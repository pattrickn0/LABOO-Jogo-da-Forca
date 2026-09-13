package br.edu.iff.jogoforca.dominio.jogador;

import br.edu.iff.factory.EntityFactory;

public class JogadorFactoryImpl extends EntityFactory implements JogadorFactory {
    private static JogadorFactoryImpl soleInstance;
    private JogadorRepository jogadorRepository;

    private JogadorFactoryImpl(JogadorRepository repository) {
        super(repository);
        this.jogadorRepository = repository;
    }

    public static synchronized void createSoleInstance(JogadorRepository repository) {
        soleInstance = new JogadorFactoryImpl(repository);
    }

    public static synchronized JogadorFactoryImpl getSoleInstance() {
        return soleInstance;
    }

    private JogadorRepository getJogadorRepository() {
        return this.jogadorRepository;
    }

    @Override
    public Jogador getJogador(String nome) {
        long novoId = getProximoId();
        return new Jogador(novoId, nome);
    }
}
