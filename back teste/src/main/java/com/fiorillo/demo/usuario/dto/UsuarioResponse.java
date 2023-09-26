package com.fiorillo.demo.usuario.dto;

import com.fiorillo.demo.usuario.enums.ENivelUsuario;
import com.fiorillo.demo.usuario.model.Usuario;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioResponse {
    private String nome;
    private String email;
    private ENivelUsuario nivelUsuario;

    public static UsuarioResponse convertFrom(Usuario usuario) {
        return UsuarioResponse.builder()
                .email(usuario.getEmail())
                .nome(usuario.getNome())
                .nivelUsuario(usuario.getNivelUsuario())
                .build();
    }
}
