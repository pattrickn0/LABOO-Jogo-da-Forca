package br.edu.iff.jogoforca.dominio.rodada.embdr;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.repository.RepositoryException;

public class BDRRodadaRepository implements RodadaRepository {
    private static BDRRodadaRepository soleInstance;

    private BDRRodadaRepository() {
        // Inicialização com BD Relacional
    }

    public static BDRRodadaRepository getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BDRRodadaRepository();
        }
        return soleInstance;
    }

    @Override
    public long getProximoId() { return 0; /* Integração com BD */ }

    @Override
    public Rodada getPorId(long id) { return null; /* Integração com BD */ }

    @Override
    public Rodada[] getPorJogador(Jogador jogador) { return new Rodada[0]; /* Integração com BD */ }

    @Override
    public void inserir(Rodada rodada) throws RepositoryException { /* Integração com BD */ }

    @Override
    public void atualizar(Rodada rodada) throws RepositoryException { /* Integração com BD */ }

    @Override
    public void remover(Rodada rodada) throws RepositoryException { /* Integração com BD */ }
}
