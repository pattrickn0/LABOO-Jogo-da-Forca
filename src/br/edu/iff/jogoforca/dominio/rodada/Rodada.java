package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.dominio.ObjetoDominioImpl;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.bancodepalavras.dominio.letra.Letra;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.jogoforca.Aplicacao;

public class Rodada extends ObjetoDominioImpl {
    private static int maxPalavras = 3;
    private static int maxErros = 10;
    private static int pontosQuandoDescobreTodasAsPalavras = 100;
    private static int pontosPorLetraEncoberta = 15;

    private Item[] itens;
    private Letra[] erradas;
    private Jogador jogador;

    public Rodada(long id, Palavra[] palavras, Jogador jogador) {
        super(id);
        this.jogador = jogador;
        this.itens = new Item[palavras.length];
        for (int i = 0; i < palavras.length; i++) {
            this.itens[i] = new Item(palavras[i]);
        }
        this.erradas = new Letra[0];
    }

    public Rodada(long id, Item[] itens, Letra[] erradas, Jogador jogador) {
        super(id);
        this.itens = itens;
        this.erradas = erradas;
        this.jogador = jogador;
    }

    public static int getMaxPalavras() { return maxPalavras; }
    public static void setMaxPalavras(int max) { maxPalavras = max; }

    public static int getMaxErros() { return maxErros; }
    public static void setMaxErros(int max) { maxErros = max; }

    public static int getPontosQuandoDescobreTodasAsPalavras() { return pontosQuandoDescobreTodasAsPalavras; }
    public static void setPontosQuandoDescobreTodasAsPalavras(int pontos) { pontosQuandoDescobreTodasAsPalavras = pontos; }

    public static int getPontosPorLetraEncoberta() { return pontosPorLetraEncoberta; }
    public static void setPontosPorLetraEncoberta(int pontos) { pontosPorLetraEncoberta = pontos; }

    public Jogador getJogador() { return jogador; }

    public Tema getTema() {
        if (itens.length > 0) {
            return itens[0].getPalavra().getTema();
        }
        return null;
    }

    public Palavra[] getPalavras() {
        Palavra[] palavras = new Palavra[itens.length];
        for (int i = 0; i < itens.length; i++) {
            palavras[i] = itens[i].getPalavra();
        }
        return palavras;
    }

    public int getNumPalavras() {
        return itens.length;
    }

    public void tentar(char codigo) {
        if (encerrou()) return;
        
        boolean achou = false;
        for (Item item : itens) {
            if (item.tentar(codigo)) {
                achou = true;
            }
        }
        
        if (!achou) {
            boolean jaTentada = false;
            for (Letra l : erradas) {
                if (l.getCodigo() == codigo) jaTentada = true;
            }
            if (!jaTentada) {
                Letra novaLetra = Aplicacao.getSoleInstance().getLetraFactory().getLetra(codigo);
                Letra[] novasErradas = new Letra[erradas.length + 1];
                System.arraycopy(erradas, 0, novasErradas, 0, erradas.length);
                novasErradas[erradas.length] = novaLetra;
                erradas = novasErradas;
            }
        }
        
        if (encerrou()) {
            jogador.setPontuacao(jogador.getPontuacao() + calcularPontos());
        }
    }

    public void arriscar(String[] palavras) {
        if (encerrou()) return;
        
        for (int i = 0; i < itens.length; i++) {
            if (i < palavras.length) {
                itens[i].arriscar(palavras[i]);
            }
        }
        
        if (encerrou()) {
            jogador.setPontuacao(jogador.getPontuacao() + calcularPontos());
        }
    }

    public void exibirItens(Object contexto) {
        for (Item item : itens) {
            item.exibir(contexto);
        }
    }

    public void exibirBoneco(Object contexto) {
        Aplicacao.getSoleInstance().getBonecoFactory().getBoneco().exibir(contexto, getQtdeErros());
    }

    public void exibirPalavras(Object contexto) {
        for (Palavra p : getPalavras()) {
            p.exibir(contexto);
        }
    }

    public void exibirErros(Object contexto) {
        for (Letra l : erradas) {
            l.exibir(contexto);
        }
    }

    public Letra[] getErros() { return erradas; }
    
    public int getQtdeErros() { return erradas.length; }

    public int getQtdeAcertos() {
        int acertos = 0;
        for (Item item : itens) {
            acertos += item.getLetrasDescobertas().length;
        }
        return acertos;
    }

    public int getQtdeTentativasRestantes() {
        return maxErros - getQtdeErros();
    }

    public boolean encerrou() {
        return arriscou() || descobriu() || getQtdeErros() >= maxErros;
    }

    public boolean descobriu() {
        for (Item item : itens) {
            if (!item.descobriu()) return false;
        }
        return true;
    }

    public boolean arriscou() {
        return itens.length > 0 && itens[0].getPalavraArriscada() != null;
    }

    public int getQtdeLetrasEncobertas() {
        int encobertas = 0;
        for (Item item : itens) {
            encobertas += item.getQtdeLetrasEncobertas();
        }
        return encobertas;
    }

    public int calcularPontos() {
        if (descobriu()) {
            int pontos = pontosQuandoDescobreTodasAsPalavras;
            for (Item item : itens) {
                pontos += item.calcularPontosLetrasEncobertas(pontosPorLetraEncoberta);
            }
            return pontos;
        }
        return 0;
    }
}
