package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance;

    private static final String[] PARTES = {
        "cabeça", "tronco", "braço esquerdo", "braço direito", "perna esquerda", "perna direita"
    };

    private BonecoTexto() {}

    public static synchronized BonecoTexto getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTexto();
        }
        return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int parte) {
        if (parte > 0 && parte <= PARTES.length) {
            System.out.print(PARTES[parte - 1]);
        }
    }
}
