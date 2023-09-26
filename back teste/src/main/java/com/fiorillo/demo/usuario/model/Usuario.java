package com.fiorillo.demo.usuario.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fiorillo.demo.usuario.dto.UsuarioRegisterRequest;
import com.fiorillo.demo.usuario.enums.ENivelUsuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "USUARIO")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @SequenceGenerator(name = "SEQ_USUARIO", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(generator = "SEQ_USUARIO", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "NOME", length = 100, nullable = false)
    private String nome;

    @Email
    @Size(max = 80)
    @Column(name = "EMAIL", nullable = false, length = 80)
    private String email;

    @JsonIgnore
    @Column(name = "SENHA", nullable = false, updatable = false, length = 80)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "NIVEL", nullable = false)
    private ENivelUsuario nivelUsuario;

    public static Usuario of(UsuarioRegisterRequest request, String senha) {
        var usuario = new Usuario();
        usuario.setNome(request.nome());
        usuario.setEmail(request.email());
        usuario.setSenha(senha);
        usuario.setNivelUsuario(request.nivel());
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.nivelUsuario == ENivelUsuario.GERENTE) return List.of(new SimpleGrantedAuthority("NIVEL_GERENTE"));
        else return List.of(new SimpleGrantedAuthority("NIVEL_COMPRADOR"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
