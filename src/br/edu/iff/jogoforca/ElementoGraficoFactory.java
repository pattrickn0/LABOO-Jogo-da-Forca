package br.edu.iff.jogoforca;

import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;

public interface ElementoGraficoFactory {
    BonecoFactory getBonecoFactory();
    LetraFactory getLetraFactory();
}
