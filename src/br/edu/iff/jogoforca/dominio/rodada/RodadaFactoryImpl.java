package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.factory.EntityFactory;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;

public abstract class RodadaFactoryImpl extends EntityFactory implements RodadaFactory {
    protected RodadaRepository rodadaRepository;
    protected TemaRepository temaRepository;
    protected PalavraRepository palavraRepository;

    protected RodadaFactoryImpl(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(rodadaRepository);
        this.rodadaRepository = rodadaRepository;
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
    }

    protected RodadaRepository getRodadaRepository() {
        return rodadaRepository;
    }

    protected TemaRepository getTemaRepository() {
        return temaRepository;
    }

    protected PalavraRepository getPalavraRepository() {
        return palavraRepository;
    }
}
