package com.horarios.SGH.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.horarios.SGH.Service.AuthService;
import com.horarios.SGH.DTO.*;
import com.horarios.SGH.Model.users;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO req) {
        return ResponseEntity.ok(authService.login(req));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody users u) {
        return ResponseEntity.ok(authService.register(u));
    }
}
