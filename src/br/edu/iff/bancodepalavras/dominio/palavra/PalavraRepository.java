package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.repository.Repository;
import br.edu.iff.repository.RepositoryException;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;

public interface PalavraRepository extends Repository {
    Palavra getPorId(long id);
    Palavra[] getPorTema(Tema tema);
    Palavra[] getTodas();
    Palavra getPalavra(String palavra);
    void inserir(Palavra palavra) throws RepositoryException;
    void atualizar(Palavra palavra) throws RepositoryException;
    void remover(Palavra palavra) throws RepositoryException;
}
