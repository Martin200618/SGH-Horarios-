package com.horarios.SGH.Controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.horarios.SGH.DTO.teachersDTO;
import com.horarios.SGH.Service.teachersService;

@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost:5500"})
@RestController
@RequestMapping("/teachers")
public class teachersController {
    @Autowired
    private teachersService teachersService;

    //registrar un nuevo profesores
    @PostMapping
        public ResponseEntity<Object> registerArtista(@RequestBody teachersDTO teachers) {
        String response = teachersService.save(teachers);
        boolean isSuccessful = response.startsWith("200");
    
        // Respuesta estructurada
        return new ResponseEntity<>(
            Map.of(
                "message", response,
                "status", isSuccessful ? "success" : "error"
            ),
            isSuccessful ? HttpStatus.OK : HttpStatus.BAD_REQUEST
        );
    }

    // Obtener todos los profesores
    @GetMapping("/")
    public ResponseEntity<Object> getAllTeachers() {
        try {
            return new ResponseEntity<>(
                teachersService.findAll(),
                HttpStatus.OK
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al obtener los profesores", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Obtener un único profesor por ID
    @GetMapping("/get/{teacherId}")
    public ResponseEntity<Object> getOneTeacher(@PathVariable("teacherId") int teacherId) {
        Optional<Object> teacher = Optional.ofNullable(teachersService.findById(teacherId));
        return teacher.map(value -> new ResponseEntity<>(
            value,
            HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(
            Map.of("message", "Profesor no encontrado"),
            HttpStatus.NOT_FOUND
        ));
    }

    // Actualizar un profesor por Id
    @PostMapping("/update/{teacherId}")
    public ResponseEntity<Object> updateteacher(@PathVariable("teacherId") int teacherId, @RequestBody teachersDTO teacher) {
        try {
            String response = teachersService.update(teacherId, teacher);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al actualizar el artista", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    // Eliminar un profesor por ID
    @DeleteMapping("/delete/{teacherId}")
    public ResponseEntity<Object> deleteTeacher(@PathVariable("teacherId") int teacherId) {
        try {
            String response = teachersService.delete(teacherId);
            return new ResponseEntity<>(
                Map.of(
                    "message", response,
                    "status", response.startsWith("200") ? "success" : "error"
                ),
                response.startsWith("200") ? HttpStatus.OK : HttpStatus.BAD_REQUEST
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                Map.of("message", "Error al eliminar el profesor", "error", e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}