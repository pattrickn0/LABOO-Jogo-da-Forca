package ModuloLetra;

public class BDRPalavraRepository implements PalavraRepository {
    private static BDRPalavraRepository soleInstance;

    private BDRPalavraRepository() {}

    public static synchronized BDRPalavraRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRPalavraRepository();
        }
        return soleInstance;
    }

    public long getProximoId() { return 0; }

    public Palavra getPorId(long id) { return null; }

    public Palavra[] getPorTema(Tema tema) { return new Palavra[0]; }

    public Palavra[] getTodas() { return new Palavra[0]; }

    public Palavra getPalavra(String palavra) { return null; }

    public void inserir(Palavra palavra) throws RunTimeException {}

    public void atualizar(Palavra palavra) throws RepositoryException {}

    public void remover(Palavra palavra) throws RepositoryException {}
}
