package br.edu.iff.jogoforca.dominio.boneco.imagem;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

public class BonecoImagem implements Boneco {
    private static BonecoImagem soleInstance;

    private BonecoImagem() {}

    public static synchronized BonecoImagem getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoImagem();
        }
        return soleInstance;
    }

    @Override
    public void exibir(Object contexto, int parte) {
        // Conforme instruções, manter método vazio
    }
}
