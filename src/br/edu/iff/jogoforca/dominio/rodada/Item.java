package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private int id;
    private Palavra palavra;
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;

    private Item(int id, Palavra palavra) {
        this.id = id;
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
    }

    private Item(int id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        this.id = id;
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
        if (posicoesDescobertas != null) {
            for (int pos : posicoesDescobertas) {
                if (pos >= 0 && pos < this.posicoesDescobertas.length) {
                    this.posicoesDescobertas[pos] = true;
                }
            }
        }
        this.palavraArriscada = palavraArriscada;
    }

    static Item criar(int id, Palavra palavra) {
        return new Item(id, palavra);
    }

    public static Item reconstituir(int id, Palavra palavra, int[] posicoesDescobertas, String palavraArriscada) {
        return new Item(id, palavra, posicoesDescobertas, palavraArriscada);
    }

    public int getId() {
        return id;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public Letra[] getLetrasDescobertas() {
        List<Letra> letras = new ArrayList<>();
        Letra[] todasLetras = palavra.getLetras();
        for (int i = 0; i < posicoesDescobertas.length; i++) {
            if (posicoesDescobertas[i]) {
                letras.add(todasLetras[i]);
            }
        }
        return letras.toArray(new Letra[0]);
    }

    public Letra[] getLetrasEncobertas() {
        List<Letra> letras = new ArrayList<>();
        Letra[] todasLetras = palavra.getLetras();
        for (int i = 0; i < posicoesDescobertas.length; i++) {
            if (!posicoesDescobertas[i]) {
                letras.add(todasLetras[i]);
            }
        }
        return letras.toArray(new Letra[0]);
    }

    public int getQtdeLetrasEncobertas() {
        int count = 0;
        for (boolean descoberta : posicoesDescobertas) {
            if (!descoberta) count++;
        }
        return count;
    }

    public int calcularPontosLetrasEncobertas(int valorPorLetraEncoberta) {
        return getQtdeLetrasEncobertas() * valorPorLetraEncoberta;
    }

    public boolean descobriu() {
        return acertou() || getQtdeLetrasEncobertas() == 0;
    }

    public void exibir(Object contexto) {
        palavra.exibir(contexto, posicoesDescobertas);
    }

    public boolean tentar(char codigo) {
        boolean achou = false;
        if (palavraArriscada == null) {
            int[] posicoes = palavra.tentar(codigo);
            if (posicoes != null && posicoes.length > 0) {
                achou = true;
                for (int pos : posicoes) {
                    posicoesDescobertas[pos] = true;
                }
            }
        }
        return achou;
    }

    public void arriscar(String palavra) {
        this.palavraArriscada = palavra;
    }

    public String getPalavraArriscada() {
        return palavraArriscada;
    }

    public boolean acertou() {
        return palavraArriscada != null && palavra.comparar(palavraArriscada);
    }
}
