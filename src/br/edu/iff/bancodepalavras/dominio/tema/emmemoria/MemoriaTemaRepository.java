package br.edu.iff.bancodepalavras.dominio.tema.emmemoria;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaTemaRepository implements TemaRepository {
    private static MemoriaTemaRepository soleInstance;
    private final List<Tema> pool = new ArrayList<>();
    private long idCounter = 1;

    private MemoriaTemaRepository() {}

    public static synchronized MemoriaTemaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaTemaRepository();
        }
        return soleInstance;
    }

    @Override
    public long getProximoId() {
        return idCounter++;
    }

    @Override
    public Tema getPorId(long id) {
        for (Tema t : pool) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    @Override
    public Tema[] getPorNome(String nome) {
        List<Tema> resultado = new ArrayList<>();
        for (Tema t : pool) {
            if (t.getNome() != null && t.getNome().equalsIgnoreCase(nome)) {
                resultado.add(t);
            }
        }
        return resultado.toArray(new Tema[0]);
    }

    @Override
    public Tema[] getTodos() {
        return pool.toArray(new Tema[0]);
    }

    @Override
    public void inserir(Tema tema) throws RepositoryException {
        if (tema == null) throw new RepositoryException("Tema nulo.");
        pool.add(tema);
    }

    @Override
    public void atualizar(Tema tema) throws RepositoryException {
        remover(tema);
        inserir(tema);
    }

    @Override
    public void remover(Tema tema) throws RepositoryException {
        pool.removeIf(t -> t.getId() == tema.getId());
    }
}
