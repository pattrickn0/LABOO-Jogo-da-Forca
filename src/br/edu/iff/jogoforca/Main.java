package br.edu.iff.jogoforca;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        try {
            RodadaAppService rodadaService = RodadaAppService.getSoleInstance();
            Rodada rodada = rodadaService != null ? rodadaService.novaRodada("Membro") : null;
            Scanner scanner = new Scanner(System.in);

            System.out.println("====== INÍCIO DO JOGO DA FORCA ======");
            if (rodada != null && rodada.getTema() != null) {
                System.out.println("Tema: " + rodada.getTema().getNome());
            }

            while (rodada != null && !rodada.encerrou()) {
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

            if (rodada != null) {
                rodadaService.salvarRodada(rodada);

                System.out.println("\n====== FIM DE JOGO ======");
                rodada.exibirBoneco(System.out);
                rodada.exibirPalavras(System.out);
                
                if (rodada.descobriu()) {
                    System.out.println("Parabéns, você venceu! Pontos ganhos: " + rodada.calcularPontos());
                } else {
                    System.out.println("Você perdeu... A(s) palavra(s) foram arriscadas incorretamente ou você atingiu o limite de erros.");
                }
            }
            
            scanner.close();

        } catch (Exception e) {
            System.out.println("Erro Crítico: " + e.getMessage());
        }
    }
}
