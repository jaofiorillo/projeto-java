package com.fiorillo.demo.usuario.repository;

import com.fiorillo.demo.usuario.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findById(Integer id);

    UserDetails findByEmail(String email);
}
