package ModuloLetra;

import java.util.List;

public interface PalavraRepository extends Repository {
    Palavra getPorId(long id);
    Palavra[] getPorTema(Tema tema);
    Palavra[] getTodas();
    Palavra getPalavra(String palavra);
    void inserir(Palavra palavra) throws RepositoryException;
    void atualizar(Palavra palavra) throws RepositoryException;
    void remover(Palavra palavra) throws RepositoryException;
}
