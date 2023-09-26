package com.fiorillo.demo.usuario.dto;

import com.fiorillo.demo.usuario.enums.ENivelUsuario;

public record UsuarioRegisterRequest(String email, String nome, String senha, ENivelUsuario nivel) {
}
