package com.horarios.SGH.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.horarios.SGH.Model.users;
import com.horarios.SGH.Service.usersService;

@RestController
@RequestMapping("/users")
public class usersController {

    @Autowired
    private usersService usersService;

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<users>> getUserById(@PathVariable int id) {
        try {
            Optional<users> user = usersService.findById(id);
            return user.isPresent() ? ResponseEntity.ok(user) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint para iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String userName, @RequestParam String password) {
        String response = usersService.login(userName, password);
        return response.equals("Inicio de sesión exitoso") ? ResponseEntity.ok(response)
                : ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
}
