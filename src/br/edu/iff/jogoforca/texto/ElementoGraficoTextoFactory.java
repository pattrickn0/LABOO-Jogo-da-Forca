package br.edu.iff.jogoforca.texto;

import br.edu.iff.jogoforca.ElementoGraficoFactory;
import br.edu.iff.jogoforca.dominio.boneco.BonecoFactory;
import br.edu.iff.jogoforca.dominio.boneco.texto.BonecoTextoFactory;
import br.edu.iff.bancodepalavras.dominio.letra.LetraFactory;
import br.edu.iff.bancodepalavras.dominio.letra.texto.LetraTextoFactory;

public class ElementoGraficoTextoFactory implements ElementoGraficoFactory {
    private static ElementoGraficoTextoFactory soleInstance;

    private ElementoGraficoTextoFactory() {}

    public static synchronized ElementoGraficoTextoFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new ElementoGraficoTextoFactory();
        }
        return soleInstance;
    }

    @Override
    public BonecoFactory getBonecoFactory() {
        return BonecoTextoFactory.getSoleInstance();
    }

    @Override
    public LetraFactory getLetraFactory() {
        return LetraTextoFactory.getSoleInstance();
    }
}
