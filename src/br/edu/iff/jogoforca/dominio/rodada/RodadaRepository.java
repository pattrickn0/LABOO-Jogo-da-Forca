package br.edu.iff.jogoforca.dominio.rodada;

import br.edu.iff.repository.Repository;
import br.edu.iff.repository.RepositoryException;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;

public interface RodadaRepository extends Repository {
    Rodada getPorId(long id);
    Rodada[] getPorJogador(Jogador jogador);
    void inserir(Rodada rodada) throws RepositoryException;
    void atualizar(Rodada rodada) throws RepositoryException;
    void remover(Rodada rodada) throws RepositoryException;
}
