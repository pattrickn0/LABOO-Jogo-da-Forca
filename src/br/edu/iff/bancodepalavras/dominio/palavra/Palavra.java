package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
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

    private Palavra(long id, String palavra, Tema tema) {
        super(id);
        this.tema = tema;
        this.letras = new Letra[palavra.length()];
        for (int i = 0; i < palavra.length(); i++) {
            this.letras[i] = letraFactory.getLetra(palavra.charAt(i));
        }
    }

    public static Palavra criar(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    public static Palavra reconstituir(long id, String palavra, Tema tema) {
        return new Palavra(id, palavra, tema);
    }

    public int[] tentar(char codigo) {
        int count = 0;
        char lower = Character.toLowerCase(codigo);
        for (Letra l : letras) {
            if (Character.toLowerCase(l.getCodigo()) == lower) count++;
        }
        int[] posicoes = new int[count];
        int j = 0;
        for (int i = 0; i < letras.length; i++) {
            if (Character.toLowerCase(letras[i].getCodigo()) == lower) {
                posicoes[j++] = i;
            }
        }
        return posicoes;
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
