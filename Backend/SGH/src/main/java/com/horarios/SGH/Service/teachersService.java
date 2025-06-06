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
public class teachersService {
    @Autowired
    private Iteachers teachersRepository;

    //Obtener todos los profesores;
    public List<teachers> findAll(){
        try{
            return teachersRepository.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error al obtener los profesores: "+ e.getMessage());
        }
    }

    //Obtener profesores por id
    public Optional<teachers> findById(int teacherId){
        try{
            return teachersRepository.findById(teacherId);
        }catch(Exception e){
            throw new RuntimeException("Error al obtener el profesor por su Id: "+ teacherId+ ", Error: "+ e.getMessage());
        }
    }

    //Registrar un profesor
    public String save(teachersDTO teachersDTO){
        if(teachersDTO == null || !isValidTeacher(teachersDTO)) {
            return HttpStatus.BAD_REQUEST.toString() +": Los datos del profesor son invalidos";
        }

        try{
            teachers teacher = converToModel(teachersDTO);
            teachersRepository.save(teacher);
            return HttpStatus.OK.toString()+": Artista registrado exitosamente";
        }catch (Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString()+": Error al registrar el profesor, Detalle: "+ e.getMessage();
        }
    }

    //Actualizar un profesor
    public String update(int teacherId,teachersDTO teachersDTO){
        Optional<teachers> existingTeacher = findById(teacherId);

        if(!existingTeacher.isPresent()){
            return HttpStatus.NOT_FOUND.toString()+": El profesor con Id: "+teacherId+" no existeo ya fue eliminado";
        }

        try{
            teachers teacherToUpdate = existingTeacher.get();
            teacherToUpdate.setTeacherName(teachersDTO.getTeacherName());
            return HttpStatus.OK.toString()+": Profesor actualizado correctamente";
        }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString()+": Error al actualizar el profesor, Error: "+e.getMessage();
        }
    }

    //Eliminar un profesor
    public String delete(int teacherId){
        Optional<teachers> teacher = findById(teacherId);
        if(!teacher.isPresent()){
            return  HttpStatus.NOT_FOUND.toString()+": El profesor con id: "+teacherId+" no existe o ya fue eliminado";
        }

        try{
            teachersRepository.deleteById(teacherId);
            return HttpStatus.OK.toString()+": Profesor eliminado correctamente";
        }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString() + ": Error al eliminar el profesor, Error: " + e.getMessage();
        }
    }

    //Conversion de DTO a model
    public teachers converToModel(teachersDTO teachers){
        return new teachers();
    }

    // Validación de datos de artista
    private boolean isValidTeacher(teachersDTO teachersDTO) {
        return !teachersDTO.getTeacherName().trim().isEmpty();
    }
}