package com.fiorillo.demo.usuario.enums;

public enum ENivelUsuario {
    GERENTE("Gerente"),
    COMPRADOR("Comprador");

    private String nivel;

    ENivelUsuario(String nivel) {
        this.nivel = nivel;
    }

    public String getNivel() {
        return nivel;
    }
}
