package ModuloLetra;

public class LetraImagemFactory extends LetraFactoryImpl {
    private static LetraImagemFactory soleInstance;

    private LetraImagemFactory() {
        super();
    }

    public static synchronized LetraImagemFactory getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new LetraImagemFactory();
        }
        return soleInstance;
    }

    @Override
    protected Letra criarLetra(char codigo) {
        return new LetraImagem(codigo);
    }
}
