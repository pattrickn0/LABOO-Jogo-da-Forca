package br.edu.iff.jogoforca;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaAppService;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Aplicacao app = Aplicacao.getSoleInstance();
        app.configurar();

        try {
            TemaFactory temaFactory = app.getTemaFactory();
            TemaRepository temaRepo = app.getRepositoryFactory().getTemaRepository();

            Tema tema1 = temaFactory.getTema("Animais");
            temaRepo.inserir(tema1);
            Tema tema2 = temaFactory.getTema("Frutas");
            temaRepo.inserir(tema2);
            Tema tema3 = temaFactory.getTema("Paises");
            temaRepo.inserir(tema3);
            Tema tema4 = temaFactory.getTema("Cores");
            temaRepo.inserir(tema4);
            Tema tema5 = temaFactory.getTema("Profissoes");
            temaRepo.inserir(tema5);

            PalavraAppService palavraService = PalavraAppService.getSoleInstance();

            String[] animais = { "CACHORRO", "GATO", "ELEFANTE", "GIRAFA", "LEAO", "TIGRE", "ZEBRA", "MACACO", "CAVALO",
                    "URSO" };
            for (String p : animais)
                palavraService.novaPalavra(p, tema1.getId());

            String[] frutas = { "MACA", "BANANA", "LARANJA", "MORANGO", "UVA", "ABACAXI", "MELANCIA", "MANGA", "PERA",
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

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o nome do jogador: ");
            String nomeJogador = scanner.nextLine().trim();

            JogadorFactory jogadorFactory = app.getJogadorFactory();
            JogadorRepository jogadorRepo = app.getRepositoryFactory().getJogadorRepository();
            Jogador jogador = jogadorFactory.getJogador(nomeJogador);
            jogadorRepo.inserir(jogador);

            RodadaAppService rodadaService = RodadaAppService.getSoleInstance();
            boolean continuar = true;

            while (continuar) {
                System.out.println("\n====== INICIO DO JOGO DA FORCA ======");
                Rodada rodada = rodadaService.novaRodada(nomeJogador);

                if (rodada != null && rodada.getTema() != null) {
                    System.out.println("Tema: " + rodada.getTema().getNome());
                } else {
                    System.out.println("Não há palavras suficientes para jogar.");
                    break;
                }

                while (!rodada.encerrou()) {
                    System.out.println("\n======================================");
                    System.out.println("Erros: " + rodada.getQtdeErros() + "/" + Rodada.getMaxErros());
                    rodada.exibirBoneco(System.out);

                    System.out.println("\nPalavra(s):");
                    rodada.exibirItens(System.out);

                    System.out.println("\nLetras erradas: ");
                    rodada.exibirLetrasErradas(System.out);

                    System.out.print("\nDigite uma letra (ou 'arriscar' para tentar as palavras): ");
                    String entrada = scanner.nextLine().trim().toLowerCase();

                    if (entrada.equals("arriscar")) {
                        String[] tentativa = new String[rodada.getNumPalavras()];
                        for (int i = 0; i < rodada.getNumPalavras(); i++) {
                            System.out.print("Palavra " + (i + 1) + ": ");
                            tentativa[i] = scanner.nextLine().trim();
                        }
                        rodada.arriscar(tentativa);
                    } else if (entrada.length() == 1) {
                        char letraDigitada = entrada.charAt(0);
                        boolean jaDigitou = false;
                        for (br.edu.iff.bancodepalavras.dominio.letra.Letra l : rodada.getTentativas()) {
                            if (Character.toLowerCase(l.getCodigo()) == Character.toLowerCase(letraDigitada)) {
                                jaDigitou = true;
                                break;
                            }
                        }

                        if (jaDigitou) {
                            System.out.println(">>> Você já tentou a letra '" + letraDigitada + "'! Tente outra.");
                        } else {
                            rodada.tentar(letraDigitada);
                        }
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
