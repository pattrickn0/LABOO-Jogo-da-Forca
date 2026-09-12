package ModuloLetra;

public class PalavraFactoryImpl extends EntityFactory implements PalavraFactory {
    private static PalavraFactoryImpl soleInstance;
    private PalavraRepository palavraRepository;

    private PalavraFactoryImpl(PalavraRepository repository) {
        super(repository);
        this.palavraRepository = repository;
    }

    public static synchronized void createSoleInstance(PalavraRepository repository) {
        soleInstance = new PalavraFactoryImpl(repository);
    }

    public static synchronized PalavraFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private PalavraRepository getPalavraRepository() {
        return this.palavraRepository;
    }

    @Override
    public Palavra getPalavra(String palavra, Tema tema) {
        long novoId = getProximoId();
        return new Palavra(novoId, palavra, tema);
    }
}
