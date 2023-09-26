package com.fiorillo.demo.usuario.service;

import com.fiorillo.demo.usuario.dto.UsuarioRegisterRequest;
import com.fiorillo.demo.usuario.dto.UsuarioRequest;
import com.fiorillo.demo.usuario.model.Usuario;
import com.fiorillo.demo.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity logarUsuario(UsuarioRequest request) {
        var userNamePassoword = new UsernamePasswordAuthenticationToken(request.email(), request.senha());
        var auth = authenticationManager.authenticate(userNamePassoword);

        return ResponseEntity.ok().build();
    }

    public ResponseEntity cadastrarUsuario(UsuarioRegisterRequest request) {
        var usuario = Usuario.of(request, request.senha());
        repository.save(usuario);
        return ResponseEntity.ok().build();
    }

    private void autenticarUsuario(String senha, Usuario usuario) {
        if (senha != usuario.getSenha()) {
            throw new RuntimeException("Email ou senha inválidos");
        }
    }
}
