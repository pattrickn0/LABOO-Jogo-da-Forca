package ModuloLetra;

public class LetraTexto extends Letra {
    public LetraTexto(char codigo) {
        super(codigo);
    }

    @Override
    public void exibir(Object contexto) {
        System.out.print(getCodigo());
    }
}
