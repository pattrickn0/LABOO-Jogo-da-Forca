package dominio.rodada;

import dominio.jogador.Jogador;

public interface RodadaFactory {
    Rodada getRodada(Jogador jogador);
}