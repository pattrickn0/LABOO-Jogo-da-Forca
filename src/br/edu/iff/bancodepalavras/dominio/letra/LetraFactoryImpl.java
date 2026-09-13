package br.edu.iff.bancodepalavras.dominio.letra;

import java.util.HashMap;
import java.util.Map;

public abstract class LetraFactoryImpl implements LetraFactory {
    private final Map<Character, Letra> pool = new HashMap<>();
    private Letra encoberta;

    protected LetraFactoryImpl() {}

    @Override
    public final Letra getLetra(char codigo) {
        char normalizado = Character.toUpperCase(codigo);
        if (!pool.containsKey(normalizado)) {
            pool.put(normalizado, criarLetra(normalizado));
        }
        return pool.get(normalizado);
    }

    @Override
    public final Letra getLetraEncoberta() {
        if (this.encoberta == null) {
            this.encoberta = criarLetra('*');
        }
        return this.encoberta;
    }

    protected abstract Letra criarLetra(char codigo);
}
