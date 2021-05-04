package com.github.fabioaspassos.controller;

import com.github.fabioaspassos.dto.CredentialsDto;
import com.github.fabioaspassos.dto.TokenDto;
import com.github.fabioaspassos.entity.Usuario;
import com.github.fabioaspassos.exception.InvalidPasswordException;
import com.github.fabioaspassos.secutity.JwtService;
import com.github.fabioaspassos.service.UserDetailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RequiredArgsConstructor

@CrossOrigin
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final UserDetailServiceImpl userDetailService;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody @Valid Usuario usuario){
        return userDetailService.create(usuario);
    }

    @PostMapping("/auth")
    public TokenDto authenticate(@RequestBody CredentialsDto credentials){
        try {
            Usuario usuario = Usuario.builder()
                    .login(credentials.getLogin())
                    .password(credentials.getPassword()).build();

            UserDetails userAuthenticated = userDetailService.authenticate(usuario);

            String token = jwtService.getToken(usuario);
            return new TokenDto(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | InvalidPasswordException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
