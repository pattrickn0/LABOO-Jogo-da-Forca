package aplicacao;

import dominio.rodada.Rodada;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        // Considerando que as palavras base e jogador já foram persistidos previamente por outro serviço.
        try {
            RodadaAppService rodadaService = RodadaAppService.getSoleInstance();
            Rodada rodada = rodadaService.novaRodada("Membro"); // Exemplo de nome cadastrado
            Scanner scanner = new Scanner(System.in);

            System.out.println("====== INÍCIO DO JOGO DA FORCA ======");
            System.out.println("Tema: " + rodada.getTema().getNome());

            while (!rodada.encerrou()) {
                System.out.println("\nErros: " + rodada.getQtdeErros() + "/" + Rodada.getMaxErros());
                rodada.exibirBoneco(System.out);
                rodada.exibirPalavras(System.out);

                System.out.println("\nDigite uma letra (ou 'arriscar' para tentar as palavras): ");
                String entrada = scanner.nextLine().trim().toLowerCase();

                if (entrada.equals("arriscar")) {
                    String[] tentativa = new String[rodada.getNumPalavras()];
                    for (int i = 0; i < rodada.getNumPalavras(); i++) {
                        System.out.print("Palavra " + (i + 1) + ": ");
                        tentativa[i] = scanner.nextLine().trim();
                    }
                    rodada.arriscar(tentativa);
                } else if (entrada.length() == 1) {
                    rodada.tentar(entrada.charAt(0));
                } else {
                    System.out.println("Entrada inválida.");
                }
            }

            rodadaService.salvarRodada(rodada);

            System.out.println("\n====== FIM DE JOGO ======");
            rodada.exibirBoneco(System.out);
            rodada.exibirPalavras(System.out);
            
            if (rodada.descobriu()) {
                System.out.println("Parabéns, você venceu! Pontos ganhos: " + rodada.calcularPontos());
            } else {
                System.out.println("Você perdeu... A(s) palavra(s) foram arriscadas incorretamente ou você atingiu o limite de erros.");
            }
            
            scanner.close();

        } catch (JogadorNaoEncontradoException e) {
            System.out.println("Erro Crítico: " + e.getMessage());
        }
    }
}