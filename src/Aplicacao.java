package aplicacao;

import dominio.RepositoryFactory;
import dominio.tema.TemaFactory;
import dominio.palavra.PalavraFactory;
import dominio.jogador.JogadorFactory;
import dominio.rodada.RodadaFactory;
import dominio.grafico.ElementoGraficoFactory;
import dominio.grafico.BonecoFactory;
import dominio.grafico.LetraFactory;

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
        // interligando os factories de acordo com os "tipos" setados nas propriedades acima.
        // A lógica instancia RepositoryFactory dependendo de tipoRepositoryFactory,
        // E injeta no RodadaAppService.
    }

    public String[] getTiposRepositoryFactory() { return TIPOS_REPOSITORY_FACTORY; }
    
    public void setTipoRepositoryFactory(String tipo) { this.tipoRepositoryFactory = tipo; }
    
    public RepositoryFactory getRepositoryFactory() {
        // Retorna a factory injetada via configurar()
        return null; 
    }

    public String[] getTiposElementoGraficoFactory() { return TIPOS_ELEMENTO_GRAFICO_FACTORY; }
    
    public void setTipoElementoGraficoFactory(String tipo) { this.tipoElementoGraficoFactory = tipo; }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        // Retorna a factory gráfica configurada.
        return null;
    }

    public BonecoFactory getBonecoFactory() {
        return (BonecoFactory) this.getElementoGraficoFactory();
    }

    public LetraFactory getLetraFactory() {
        return (LetraFactory) this.getElementoGraficoFactory();
    }

    public String[] getTiposRodadaFactory() { return TIPOS_RODADA_FACTORY; }
    
    public void setTipoRodadaFactory(String tipo) { this.tipoRodadaFactory = tipo; }

    public RodadaFactory getRodadaFactory() {
        // Retorna singleton configurado (ex: RodadaSorteioFactory.getSoleInstance())
        return null; 
    }

    public TemaFactory getTemaFactory() { return null; }
    public PalavraFactory getPalavraFactory() { return null; }
    public JogadorFactory getJogadorFactory() { return null; }
}