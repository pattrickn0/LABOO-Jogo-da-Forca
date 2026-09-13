package br.edu.iff.jogoforca.dominio.jogador.emmemoria;

import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.jogoforca.dominio.jogador.JogadorRepository;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaJogadorRepository implements JogadorRepository {
    private static MemoriaJogadorRepository soleInstance;
    private final List<Jogador> pool = new ArrayList<>();
    private long idCounter = 1;

    private MemoriaJogadorRepository() {}

    public static synchronized MemoriaJogadorRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaJogadorRepository();
        }
        return soleInstance;
    }

    @Override
    public long getProximoId() {
        return idCounter++;
    }

    @Override
    public Jogador getPorId(long id) {
        for (Jogador j : pool) {
            if (j.getId() == id) return j;
        }
        return null;
    }

    @Override
    public Jogador getPorNome(String nome) {
        for (Jogador j : pool) {
            if (j.getNome() != null && j.getNome().equalsIgnoreCase(nome)) {
                return j;
            }
        }
        return null;
    }

    @Override
    public Jogador[] getTodos() {
        return pool.toArray(new Jogador[0]);
    }

    @Override
    public void inserir(Jogador jogador) throws RepositoryException {
        if (jogador == null) throw new RepositoryException("Jogador nulo.");
        pool.add(jogador);
    }

    @Override
    public void atualizar(Jogador jogador) throws RepositoryException {
        remover(jogador);
        inserir(jogador);
    }

    @Override
    public void remover(Jogador jogador) throws RepositoryException {
        pool.removeIf(j -> j.getId() == jogador.getId());
    }
}
