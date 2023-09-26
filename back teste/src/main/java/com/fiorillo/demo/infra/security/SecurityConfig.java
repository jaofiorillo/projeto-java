package com.fiorillo.demo.infra.security;

import com.fiorillo.demo.usuario.enums.ENivelUsuario;
import javax.servlet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Autowired
    CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/usuario/cadastrar").permitAll()
                                .requestMatchers(HttpMethod.GET, "api/orcamento/listar").permitAll()
                                .requestMatchers(HttpMethod.POST, "api/usuario/orcamento/cadastrar")
                                .hasRole(ENivelUsuario.COMPRADOR.getNivel())
                                .requestMatchers(HttpMethod.POST, "api/orcamento/gerencia/**")
                                .hasRole(ENivelUsuario.GERENTE.getNivel())
                                .anyRequest().authenticated())
                .cors(cors -> cors.disable())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
