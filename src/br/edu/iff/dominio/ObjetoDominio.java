package br.edu.iff.dominio;

public abstract class ObjetoDominio {
    private long id;

    public ObjetoDominio(long id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
