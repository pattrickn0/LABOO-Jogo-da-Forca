package ModuloLetra;

import java.util.ArrayList;
import java.util.List;

public class MemoriaPalavraRepository implements PalavraRepository {
    private static MemoriaPalavraRepository soleInstance;
    private final List<Palavra> pool = new ArrayList<>();

    private MemoriaPalavraRepository() {}

    public static synchronized MemoriaPalavraRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaPalavraRepository();
        }
        return soleInstance;
    }

    
    public long getProximoId() {
        return pool.size() + 1L;
    }

    
    public Palavra getPorId(long id) {
        for (Palavra p : pool) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    
    public Palavra[] getPorTema(Tema tema) {
        List<Palavra> resultado = new ArrayList<>();
        for (Palavra p : pool) {
            if (p.getTema() != null && p.getTema().equals(tema)) {
                resultado.add(p);
            }
        }
        return resultado.toArray(new Palavra[0]);
    }

    public Palavra[] getTodas() {
        return pool.toArray(new Palavra[0]);
    }

    public Palavra getPalavra(String palavra) {
        for (Palavra p : pool) {
            if (p.comparar(palavra)) {
                return p;
            }
        }
        return null;
    }

    public void inserir(Palavra palavra) throws RepositoryException {
        if (palavra == null) throw new RepositoryException("Palavra nula.");
        pool.add(palavra);
    }

    public void atualizar(Palavra palavra) throws RepositoryException {
        remover(palavra);
        inserir(palavra);
    }

    public void remover(Palavra palavra) throws RepositoryException {
        pool.removeIf(p -> p.getId() == palavra.getId());
    }

}
