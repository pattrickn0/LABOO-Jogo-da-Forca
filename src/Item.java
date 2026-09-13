package dominio.rodada;

import dominio.palavra.Palavra;
import dominio.palavra.Letra;
import aplicacao.Aplicacao;
import java.util.ArrayList;
import java.util.List;

public class Item {
    private Palavra palavra;
    private boolean[] posicoesDescobertas;
    private String palavraArriscada = null;

    public Item(Palavra palavra) {
        this.palavra = palavra;
        this.posicoesDescobertas = new boolean[palavra.getTamanho()];
    }

    public Item(Palavra palavra, boolean[] posicoesDescobertas, String palavraArriscada) {
        this.palavra = palavra;
        this.posicoesDescobertas = posicoesDescobertas;
        this.palavraArriscada = palavraArriscada;
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
            Letra[] letras = palavra.getLetras();
            for (int i = 0; i < letras.length; i++) {
                if (letras[i].getCodigo() == codigo) {
                    posicoesDescobertas[i] = true;
                    achou = true;
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