package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoTexto implements Boneco {
    private static BonecoTexto soleInstance;

    private static final String[] PARTES = {
        "cabeça", "olho esquerdo", "olho direito", "nariz", "boca", "tronco", 
        "braço esquerdo", "braço direito", "perna esquerda", "perna direita"
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
        if (parte > 0) {
            int limite = Math.min(parte, PARTES.length);
            for (int i = 0; i < limite; i++) {
                System.out.print(PARTES[i]);
                if (i < limite - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }
}
