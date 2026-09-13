package br.edu.iff.jogoforca;

import br.edu.iff.bancodepalavras.dominio.tema.TemaFactory;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactory;
import br.edu.iff.jogoforca.dominio.jogador.JogadorFactory;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactory;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;

public class Aplicacao {
    private static final String[] TIPOS_REPOSITORY_FACTORY = {"memoria", "relacional"};
    private static final String[] TIPOS_ELEMENTO_GRAFICO_FACTORY = {"texto", "imagem"};
    private static final String[] TIPOS_RODADA_FACTORY = {"sorteio"};

    private static Aplicacao soleInstance;

    private String tipoRepositoryFactory = TIPOS_REPOSITORY_FACTORY[0];
    private String tipoElementoGraficoFactory = TIPOS_ELEMENTO_GRAFICO_FACTORY[0];
    private String tipoRodadaFactory = TIPOS_RODADA_FACTORY[0];

    private Aplicacao() {}

    public static Aplicacao getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new Aplicacao();
        }
        return soleInstance;
    }

    public void configurar() {
        // O escopo completo da injeção de dependências ocorre aqui 
    }

    public String[] getTiposRepositoryFactory() { return TIPOS_REPOSITORY_FACTORY; }
    public void setTipoRepositoryFactory(String tipo) { this.tipoRepositoryFactory = tipo; }
    public RepositoryFactory getRepositoryFactory() { return null; }

    public String[] getTiposElementoGraficoFactory() { return TIPOS_ELEMENTO_GRAFICO_FACTORY; }
    public void setTipoElementoGraficoFactory(String tipo) { this.tipoElementoGraficoFactory = tipo; }

    private ElementoGraficoFactory getElementoGraficoFactory() { return null; }

    public BonecoFactory getBonecoFactory() {
        ElementoGraficoFactory factory = getElementoGraficoFactory();
        return factory != null ? factory.getBonecoFactory() : null;
    }

    public LetraFactory getLetraFactory() {
        ElementoGraficoFactory factory = getElementoGraficoFactory();
        return factory != null ? factory.getLetraFactory() : null;
    }

    public String[] getTiposRodadaFactory() { return TIPOS_RODADA_FACTORY; }
    public void setTipoRodadaFactory(String tipo) { this.tipoRodadaFactory = tipo; }

    public RodadaFactory getRodadaFactory() { return null; }

    public TemaFactory getTemaFactory() { return null; }
    public PalavraFactory getPalavraFactory() { return null; }
    public JogadorFactory getJogadorFactory() { return null; }
}
