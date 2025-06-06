package com.horarios.SGH.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.horarios.SGH.DTO.responseDTO;
import com.horarios.SGH.Model.users;
import com.horarios.SGH.Service.usersService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
@RequestMapping("/users")
public class usersController {

    @Autowired
    private usersService usersService;

// Obtener usuario por ID, devolviendo responseDTO en caso de error
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id) {
        try {
            Optional<users> usuarioOptional = usersService.findById(id);
            if (usuarioOptional.isPresent()) {
                return ResponseEntity.ok(usuarioOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new responseDTO("ERROR", "Usuario no encontrado"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new responseDTO("ERROR", "Error interno: " + e.getMessage()));
        }
    }

    // Endpoint para iniciar sesión, utilizando responseDTO para las respuestas
    @PostMapping("/login")
    public ResponseEntity<responseDTO> login(@RequestParam String userName, @RequestParam String password) {
        try {
            String resultMessage = usersService.login(userName, password);
            return ResponseEntity.ok(new responseDTO("OK", resultMessage));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new responseDTO("ERROR", e.getMessage()));
        }
    }
}
