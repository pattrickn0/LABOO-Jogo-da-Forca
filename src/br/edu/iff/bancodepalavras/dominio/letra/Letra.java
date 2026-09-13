package br.edu.iff.bancodepalavras.dominio.letra;

public abstract class Letra {
    private char codigo;

    public Letra(char codigo) {
        this.codigo = codigo;
    }

    public char getCodigo() {
        return this.codigo;
    }

    public abstract void exibir(Object contexto);

    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (outro == null || getClass() != outro.getClass()) return false;
        Letra outraLetra = (Letra) outro;
        return this.codigo == outraLetra.codigo;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(this.codigo);
    }

    @Override
    public final String toString() {
        return String.valueOf(this.codigo);
    }
}
