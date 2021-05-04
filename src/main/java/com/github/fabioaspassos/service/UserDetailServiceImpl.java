package com.github.fabioaspassos.service;

import com.github.fabioaspassos.entity.Usuario;
import com.github.fabioaspassos.exception.InvalidPasswordException;
import com.github.fabioaspassos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuario create(Usuario usuario){
        String pass = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(pass);

        return usuarioRepository.save(usuario);
    }

    public UserDetails authenticate(Usuario usuario){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean matches = passwordEncoder.matches(usuario.getPassword(), user.getPassword());
        if (!matches) {
            throw new InvalidPasswordException();
        }
        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(username)
                .orElseThrow(()-> new UsernameNotFoundException("Usuario nao econtrado"));

        String[] roles = usuario.isAdmin()  ?
                new String[]{"ADMIN", "USER"} : new String[]{"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getPassword())
                .roles(roles)
                .build();
    }
}
