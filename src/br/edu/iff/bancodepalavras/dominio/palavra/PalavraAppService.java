package br.edu.iff.bancodepalavras.dominio.palavra;

import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.repository.RepositoryException;

public class PalavraAppService {
    private static PalavraAppService soleInstance;
    private TemaRepository temaRepository;
    private PalavraRepository palavraRepository;
    private PalavraFactory palavraFactory;

    private PalavraAppService(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory factory) {
        this.temaRepository = temaRepository;
        this.palavraRepository = palavraRepository;
        this.palavraFactory = factory;
    }

    public static synchronized void createSoleInstance(TemaRepository temaRepository, PalavraRepository palavraRepository, PalavraFactory palavraFactory) {
        soleInstance = new PalavraAppService(temaRepository, palavraRepository, palavraFactory);
    }

    public static synchronized PalavraAppService getSoleInstance() {
        return soleInstance;
    }

    public boolean novaPalavra(String palavra, long idTema) {
        Palavra existente = this.palavraRepository.getPalavra(palavra);
        if (existente != null) {
            return true;
        }

        Tema tema = this.temaRepository.getPorId(idTema);
        if (tema == null) {
            return false;
        }

        try {
            Palavra nova = this.palavraFactory.getPalavra(palavra, tema);
            this.palavraRepository.inserir(nova);
            return true;
        } catch (RepositoryException e) {
            return false;
        }
    }
}
