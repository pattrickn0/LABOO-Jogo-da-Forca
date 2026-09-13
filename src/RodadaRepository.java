package dominio.rodada;

import dominio.Repository;
import dominio.RepositoryException;
import dominio.jogador.Jogador;

public interface RodadaRepository extends Repository {
    Rodada getPorId(long id);
    Rodada[] getPorJogador(Jogador jogador);
    void inserir(Rodada rodada) throws RepositoryException;
    void atualizar(Rodada rodada) throws RepositoryException;
    void remover(Rodada rodada) throws RepositoryException;
}