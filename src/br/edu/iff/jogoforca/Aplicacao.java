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
        RepositoryFactory repoFactory = getRepositoryFactory();
        
        br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl.createSoleInstance(repoFactory.getTemaRepository());
        br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl.createSoleInstance(repoFactory.getPalavraRepository());
        br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl.createSoleInstance(repoFactory.getJogadorRepository());
        br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory.createSoleInstance(repoFactory.getRodadaRepository(), repoFactory.getTemaRepository(), repoFactory.getPalavraRepository());
        
        br.edu.iff.bancodepalavras.dominio.palavra.Palavra.setLetraFactory(getLetraFactory());
        br.edu.iff.jogoforca.dominio.rodada.Rodada.setBonecoFactory(getBonecoFactory());
        
        br.edu.iff.bancodepalavras.dominio.palavra.PalavraAppService.createSoleInstance(repoFactory.getTemaRepository(), repoFactory.getPalavraRepository(), getPalavraFactory());
        br.edu.iff.jogoforca.dominio.rodada.RodadaAppService.createSoleInstance(getRodadaFactory(), repoFactory.getRodadaRepository(), repoFactory.getJogadorRepository());
    }

    public String[] getTiposRepositoryFactory() { return TIPOS_REPOSITORY_FACTORY; }
    public void setTipoRepositoryFactory(String tipo) { this.tipoRepositoryFactory = tipo; }
    
    public RepositoryFactory getRepositoryFactory() {
        if (tipoRepositoryFactory.equals("memoria")) {
            return br.edu.iff.jogoforca.emmemoria.MemoriaRepositoryFactory.getSoleInstance();
        } else if (tipoRepositoryFactory.equals("relacional")) {
            return br.edu.iff.jogoforca.embdr.BDRRepositoryFactory.getSoleInstance();
        }
        return null;
    }

    public String[] getTiposElementoGraficoFactory() { return TIPOS_ELEMENTO_GRAFICO_FACTORY; }
    public void setTipoElementoGraficoFactory(String tipo) { this.tipoElementoGraficoFactory = tipo; }

    private ElementoGraficoFactory getElementoGraficoFactory() {
        if (tipoElementoGraficoFactory.equals("texto")) {
            return br.edu.iff.jogoforca.texto.ElementoGraficoTextoFactory.getSoleInstance();
        } else if (tipoElementoGraficoFactory.equals("imagem")) {
            return br.edu.iff.jogoforca.imagem.ElementoGraficoImagemFactory.getSoleInstance();
        }
        return null;
    }

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

    public RodadaFactory getRodadaFactory() {
        if (tipoRodadaFactory.equals("sorteio")) {
            return br.edu.iff.jogoforca.dominio.rodada.sorteio.RodadaSorteioFactory.getSoleInstance();
        }
        return null;
    }

    public TemaFactory getTemaFactory() { 
        return br.edu.iff.bancodepalavras.dominio.tema.TemaFactoryImpl.getSoleInstance(); 
    }
    
    public PalavraFactory getPalavraFactory() { 
        return br.edu.iff.bancodepalavras.dominio.palavra.PalavraFactoryImpl.getSoleInstance(); 
    }
    
    public JogadorFactory getJogadorFactory() { 
        return br.edu.iff.jogoforca.dominio.jogador.JogadorFactoryImpl.getSoleInstance(); 
    }
}
