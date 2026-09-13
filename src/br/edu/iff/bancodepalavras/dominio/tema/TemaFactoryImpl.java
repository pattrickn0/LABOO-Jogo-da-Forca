package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.factory.EntityFactory;

public class TemaFactoryImpl extends EntityFactory implements TemaFactory {
    private static TemaFactoryImpl soleInstance;
    private TemaRepository temaRepository;

    private TemaFactoryImpl(TemaRepository repository) {
        super(repository);
        this.temaRepository = repository;
    }

    public static synchronized void createSoleInstance(TemaRepository repository) {
        soleInstance = new TemaFactoryImpl(repository);
    }

    public static synchronized TemaFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private TemaRepository getTemaRepository() {
        return this.temaRepository;
    }

    @Override
    public Tema getTema(String nome) {
        long novoId = getProximoId();
        return Tema.criar(novoId, nome);
    }
}
