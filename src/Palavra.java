package ModuloLetra;

import java.util.Arrays;

public class Palavra extends ObjetoDominioImpl {
    private static LetraFactory letraFactory;
    private Letra[] letras;
    private Tema tema;

    public static void setLetraFactory(LetraFactory factory) {
        letraFactory = factory;
    }

    public static LetraFactory getLetraFactory() {
        return letraFactory;
    }

    public Palavra(long id, String palavra, Tema tema) {
        super(id);
        this.tema = tema;
        this.letras = new Letra[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            this.letras[i] = letraFactory.getLetra(palavra.charAt(i));
        }
    }

    public Palavra(String palavra, Tema tema) {
        this(0L, palavra, tema);
    }

    public Letra[] getLetras() {
        return Arrays.copyOf(this.letras, this.letras.length);
    }

    public Letra getLetra(int posicao) {
        if (posicao >= 0 && posicao < this.letras.length) {
            return this.letras[posicao];
        }
        return null;
    }

    public void exibir(Object contexto) {
        for (Letra l : this.letras) {
            l.exibir(contexto);
        }
    }

    public void exibir(Object contexto, boolean[] posicoes) {
        for (int i = 0; i < this.letras.length; i++) {
            if (posicoes != null && i < posicoes.length && posicoes[i]) {
                this.letras[i].exibir(contexto);
            } else {
                letraFactory.getLetraEncoberta().exibir(contexto);
            }
        }
    }

    public Tema getTema() {
        return this.tema;
    }

    public boolean comparar(String palavra) {
        return toString().equalsIgnoreCase(palavra);
    }

    public int getTamanho() {
        return this.letras.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letra l : this.letras) {
            sb.append(l.getCodigo());
        }
        return sb.toString();
    }
}