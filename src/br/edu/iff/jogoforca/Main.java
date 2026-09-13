package br.edu.iff.jogoforca;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        try {
            // 2) Criar e inserir Temas
            br.edu.iff.bancodepalavras.dominio.tema.TemaFactory temaFactory = app.getTemaFactory();
            br.edu.iff.bancodepalavras.dominio.tema.TemaRepository temaRepo = app.getRepositoryFactory()
                    .getTemaRepository();

            br.edu.iff.bancodepalavras.dominio.tema.Tema tema1 = temaFactory.getTema("Animais");
            br.edu.iff.bancodepalavras.dominio.tema.Tema tema2 = temaFactory.getTema("Frutas");
            br.edu.iff.bancodepalavras.dominio.tema.Tema tema3 = temaFactory.getTema("Paises");
            br.edu.iff.bancodepalavras.dominio.tema.Tema tema4 = temaFactory.getTema("Cores");
            br.edu.iff.bancodepalavras.dominio.tema.Tema tema5 = temaFactory.getTema("Profissoes");
            temaRepo.inserir(tema1);
            temaRepo.inserir(tema2);
            temaRepo.inserir(tema3);
            temaRepo.inserir(tema4);
            temaRepo.inserir(tema5);

            // 3) Criar/Inserir Palavras
            br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService palavraService = br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService
                    .getSoleInstance();

            String[] animais = { "CACHORRO", "GATO", "ELEFANTE", "GIRAFA", "LEÃO", "TIGRE", "ZEBRA", "MACACO", "CAVALO",
                    "URSO" };
            for (String p : animais)
                palavraService.novaPalavra(p, tema1.getId());

            String[] frutas = { "MAÇA", "BANANA", "LARANJA", "MORANGO", "UVA", "ABACAXI", "MELANCIA", "MANGA", "PERA",
                    "KIWI" };
            for (String p : frutas)
                palavraService.novaPalavra(p, tema2.getId());

            String[] paises = { "BRASIL", "ARGENTINA", "CANADA", "JAPAO", "ALEMANHA", "ITALIA", "ESPANHA", "MEXICO",
                    "PORTUGAL", "FRANCA" };
            for (String p : paises)
                palavraService.novaPalavra(p, tema3.getId());

            String[] cores = { "VERMELHO", "AZUL", "VERDE", "AMARELO", "ROXO", "LARANJA", "PRETO", "BRANCO", "MARROM",
                    "ROSA" };
            for (String p : cores)
                palavraService.novaPalavra(p, tema4.getId());

            String[] profissoes = { "MEDICO", "ENGENHEIRO", "PROFESSOR", "ADVOGADO", "PROGRAMADOR", "DESIGNER",
                    "BOMBEIRO", "POLICIAL", "CANTOR", "ATOR" };
            for (String p : profissoes)
                palavraService.novaPalavra(p, tema5.getId());

            // 4) Criar e inserir Jogador
            br.edu.iff.jogoforca.dominio.jogador.JogadorFactory jogadorFactory = app.getJogadorFactory();
            br.edu.iff.jogoforca.dominio.jogador.JogadorRepository jogadorRepo = app.getRepositoryFactory()
                    .getJogadorRepository();
            br.edu.iff.jogoforca.dominio.jogador.Jogador jogador = jogadorFactory.getJogador("Membro");
            jogadorRepo.inserir(jogador);

            // 5) Jogar em loop
            Scanner scanner = new Scanner(System.in);
            RodadaAppService rodadaService = RodadaAppService.getSoleInstance();
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n====== INÍCIO DO JOGO DA FORCA ======");
                Rodada rodada = rodadaService.novaRodada("Membro");

                if (rodada != null && rodada.getTema() != null) {
                    System.out.println("Tema: " + rodada.getTema().getNome());
                } else {
                    System.out.println("Não há palavras suficientes para jogar.");
                    break;
                }

                while (!rodada.encerrou()) {
                    System.out.println("\nErros: " + rodada.getQtdeErros() + "/" + Rodada.getMaxErros());
                    rodada.exibirBoneco(System.out);

                    System.out.println("\nPalavra(s):");
                    rodada.exibirItens(System.out);

                    System.out.println("\nLetras erradas: ");
                    rodada.exibirLetrasErradas(System.out);

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
                        System.out.println("Entrada inválida. Digite uma letra ou 'arriscar'. ");
                    }
                }

                rodadaService.salvarRodada(rodada);

                System.out.println("\n====== FIM DE JOGO ======");
                rodada.exibirBoneco(System.out);

                System.out.println("\nPalavra(s):");
                rodada.exibirPalavras(System.out);

                if (rodada.descobriu()) {
                    System.out.println("\nParabéns, você venceu! Pontos ganhos: " + rodada.calcularPontos());
                } else {
                    System.out.println(
                            "\nVocê perdeu... A(s) palavra(s) foram arriscadas incorretamente ou você atingiu o limite de erros.");
                }

                System.out.println("Pontuação total do jogador: " + rodada.getJogador().getPontuacao());

                System.out.print("\nDeseja jogar novamente? (S/N): ");
                String resposta = scanner.nextLine().trim().toUpperCase();
                continuar = resposta.equals("S");
            }

            scanner.close();

        } catch (Exception e) {
            System.out.println("Erro Crítico: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
