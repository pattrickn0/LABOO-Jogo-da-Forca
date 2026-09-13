package br.edu.iff.jogoforca.dominio.rodada.sorteio;

import br.edu.iff.jogoforca.dominio.rodada.Rodada;
import br.edu.iff.jogoforca.dominio.rodada.RodadaFactoryImpl;
import br.edu.iff.jogoforca.dominio.rodada.RodadaRepository;
import br.edu.iff.jogoforca.dominio.jogador.Jogador;
import br.edu.iff.bancodepalavras.dominio.tema.Tema;
import br.edu.iff.bancodepalavras.dominio.tema.TemaRepository;
import br.edu.iff.bancodepalavras.dominio.palavra.Palavra;
import br.edu.iff.bancodepalavras.dominio.palavra.PalavraRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RodadaSorteioFactory extends RodadaFactoryImpl {
    private static RodadaSorteioFactory soleInstance;

    public static void createSoleInstance(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        if (soleInstance == null) {
            soleInstance = new RodadaSorteioFactory(rodadaRepository, temaRepository, palavraRepository);
        }
    }

    public static RodadaSorteioFactory getSoleInstance() {
        return soleInstance;
    }

    private RodadaSorteioFactory(RodadaRepository rodadaRepository, TemaRepository temaRepository, PalavraRepository palavraRepository) {
        super(rodadaRepository, temaRepository, palavraRepository);
    }

    @Override
    public Rodada getRodada(Jogador jogador) {
        Tema[] temas = getTemaRepository().getTodos();
        if (temas == null || temas.length == 0) return null;

        Random rnd = new Random();
        Tema temaSorteado = temas[rnd.nextInt(temas.length)];
        Palavra[] palavrasDoTema = getPalavraRepository().getPorTema(temaSorteado);
        
        if (palavrasDoTema == null || palavrasDoTema.length == 0) return null;

        int qtdeSortear = rnd.nextInt(Rodada.getMaxPalavras()) + 1;
        qtdeSortear = Math.min(qtdeSortear, palavrasDoTema.length);

        List<Palavra> disponiveis = new ArrayList<>(Arrays.asList(palavrasDoTema));
        Palavra[] sorteadas = new Palavra[qtdeSortear];

        for (int i = 0; i < qtdeSortear; i++) {
            int index = rnd.nextInt(disponiveis.size());
            sorteadas[i] = disponiveis.remove(index);
        }

        return Rodada.criar(getProximoId(), sorteadas, jogador);
    }
}
