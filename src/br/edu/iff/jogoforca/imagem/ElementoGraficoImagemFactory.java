package br.edu.iff.jogoforca.imagem;

import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.boneco.imagem.BonecoImagemFactory;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.letra.imagem.LetraImagemFactory;

public class ElementoGraficoImagemFactory implements ElementoGraficoFactory {
    private static ElementoGraficoImagemFactory soleInstance;

    private ElementoGraficoImagemFactory() {}

    public static synchronized ElementoGraficoImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoImagemFactory();
        }
        return soleInstance;
    }

    @Override
    public BonecoFactory getBonecoFactory() {
        return BonecoImagemFactory.getSoleInstance();
    }

    @Override
    public LetraFactory getLetraFactory() {
        return LetraImagemFactory.getSoleInstance();
    }
}
