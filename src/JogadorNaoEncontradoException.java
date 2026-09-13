package aplicacao;

public class JogadorNaoEncontradoException extends Exception {
    private String jogador;

    public JogadorNaoEncontradoException(String jogador) {
        super("O jogador " + jogador + " não foi encontrado no repositório.");
        this.jogador = jogador;
    }

    public String getJogador() {
        return jogador;
    }
}