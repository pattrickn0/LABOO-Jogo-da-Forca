package dominio.rodada;

import dominio.EntityFactory;
import dominio.tema.TemaRepository;
import dominio.palavra.PalavraRepository;

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