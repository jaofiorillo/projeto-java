package com.fiorillo.demo.usuario.controller;

import com.fiorillo.demo.usuario.dto.UsuarioRegisterRequest;
import com.fiorillo.demo.usuario.dto.UsuarioRequest;
import com.fiorillo.demo.usuario.dto.UsuarioResponse;
import com.fiorillo.demo.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("api/usuario")
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/logar")
    public ResponseEntity logarUsuario(@RequestBody @Valid UsuarioRequest request) {
        return service.logarUsuario(request);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid UsuarioRegisterRequest request) {
        return service.cadastrarUsuario(request);
    }
}
