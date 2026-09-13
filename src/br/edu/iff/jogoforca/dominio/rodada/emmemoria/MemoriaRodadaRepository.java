package br.edu.iff.jogoforca.dominio.rodada.emmemoria;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.repository.RepositoryException;
import java.util.ArrayList;
import java.util.List;

public class MemoriaRodadaRepository implements RodadaRepository {
    private static MemoriaRodadaRepository soleInstance;
    private List<Rodada> rodadas = new ArrayList<>();
    private long idCounter = 1;

    private MemoriaRodadaRepository() {}

    public static MemoriaRodadaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new MemoriaRodadaRepository();
        }
        return soleInstance;
    }

    @Override
    public long getProximoId() {
        return idCounter++;
    }

    @Override
    public Rodada getPorId(long id) {
        for (Rodada r : rodadas) {
            if (r.getId() == id) return r;
        }
        return null;
    }

    @Override
    public Rodada[] getPorJogador(Jogador jogador) {
        List<Rodada> doJogador = new ArrayList<>();
        for (Rodada r : rodadas) {
            if (r.getJogador().equals(jogador)) {
                doJogador.add(r);
            }
        }
        return doJogador.toArray(new Rodada[0]);
    }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException {
        if (getPorId(rodada.getId()) != null) {
            throw new RepositoryException("Rodada já existente.");
        }
        rodadas.add(rodada);
    }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException {
        Rodada existente = getPorId(rodada.getId());
        if (existente == null) {
            throw new RepositoryException("Rodada não encontrada para atualização.");
        }
        rodadas.set(rodadas.indexOf(existente), rodada);
    }

    @Override
    public void remover(Rodada rodada) throws RepositoryException {
        if (!rodadas.remove(rodada)) {
            throw new RepositoryException("Rodada não encontrada para remoção.");
        }
    }
}
