package br.edu.iff.bancodepalavras.dominio.tema;

import br.edu.iff.dominio.ObjetoDominioImpl;

public class Tema extends ObjetoDominioImpl {
    private String nome;

    private Tema(long id, String nome) {
        super(id);
        this.nome = nome;
    }

    public static Tema criar(long id, String nome) {
        return new Tema(id, nome);
    }

    public static Tema reconstituir(long id, String nome) {
        return new Tema(id, nome);
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object outro) {
        if (this == outro) return true;
        if (outro == null || getClass() != outro.getClass()) return false;
        Tema outroTema = (Tema) outro;
        return this.nome != null && this.nome.equalsIgnoreCase(outroTema.nome);
    }

    @Override
    public int hashCode() {
        return nome != null ? nome.toLowerCase().hashCode() : 0;
    }
}
