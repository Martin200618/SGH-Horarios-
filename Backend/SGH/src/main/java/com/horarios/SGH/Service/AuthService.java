package com.horarios.SGH.Service;

import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.horarios.SGH.Model.users;
import com.horarios.SGH.DTO.*;
import com.horarios.SGH.Repository.Iusers;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final Iusers repo;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authManager;

    public String register(users u) {
        u.setPassword(encoder.encode(u.getPassword()));
        repo.save(u);
        return "✅ Usuario registrado correctamente";
    }

    public LoginResponseDTO login(LoginRequestDTO req) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(req.getUserName(), req.getPassword())
        );
        var user = repo.findByUserName(req.getUserName()).orElseThrow();
        var userDetails = new org.springframework.security.core.userdetails.User(
            user.getUserName(), user.getPassword(), java.util.Collections.emptyList()
        );
        String token = jwtService.generateToken(userDetails);
        return new LoginResponseDTO(token);
    }
}
