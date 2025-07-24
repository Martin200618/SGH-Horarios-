package com.horarios.SGH.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.horarios.SGH.DTO.teachersDTO;
import com.horarios.SGH.Model.teachers;
import com.horarios.SGH.Repository.Iteachers;

@Service
public class subjectsService {

    @Autowired
    private Iteachers teachersRepository;

    public List<teachers> findAll() {
        try {
            return teachersRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener los profesores: " + e.getMessage());
        }
    }

    public Optional<teachers> findById(int teacherId) {
        try {
            return teachersRepository.findById(teacherId);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar profesor ID " + teacherId + ": " + e.getMessage());
        }
    }

    public String save(teachersDTO dto) {
        if (dto == null || !isValidTeacher(dto)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del profesor son inválidos";
        }

        try {
            teachers teacher = convertToModel(dto);
            teachersRepository.save(teacher);
            return HttpStatus.OK.toString() + ": Profesor registrado exitosamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al registrar profesor: " + e.getMessage();
        }
    }

    public String update(int teacherId, teachersDTO dto) {
        Optional<teachers> existingTeacher = findById(teacherId);

        if (!existingTeacher.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El profesor con ID " + teacherId + " no existe";
        }

        try {
            teachers t = existingTeacher.get();
            t.setTeacherName(dto.getTeacherName());
            teachersRepository.save(t);
            return HttpStatus.OK.toString() + ": Profesor actualizado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al actualizar: " + e.getMessage();
        }
    }

    public String delete(int teacherId) {
        Optional<teachers> teacher = findById(teacherId);
        if (!teacher.isPresent()) {
            return HttpStatus.NOT_FOUND.toString() + ": El profesor con ID " + teacherId + " no existe";
        }

        try {
            teachersRepository.deleteById(teacherId);
            return HttpStatus.OK.toString() + ": Profesor eliminado correctamente";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar profesor: " + e.getMessage();
        }
    }

    private teachers convertToModel(teachersDTO dto) {
        teachers t = new teachers();
        t.setTeacherName(dto.getTeacherName());
        return t;
    }

    private boolean isValidTeacher(teachersDTO dto) {
        return dto.getTeacherName() != null && !dto.getTeacherName().trim().isEmpty();
    }
}
