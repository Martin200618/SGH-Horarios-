package com.horarios.SGH.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.horarios.SGH.DTO.subjectsDTO;
import com.horarios.SGH.Model.subjects;
import com.horarios.SGH.Repository.Isubjects;

@Service
public class subjectsService {
    @Autowired
    private Isubjects isubjects;

    //optener todas las materias
    public List<subjects> findAll(){
        try{
            return isubjects.findAll();
        }catch(Exception e){
            throw new RuntimeException("Error al obtener todas las materias: "+ e.getMessage());
        }
    }

    //Obtener materias por id
    public Optional<subjects> findById(int subjectId){
        try{
            return isubjects.findById(subjectId);
        }catch(Exception e){
            throw new RuntimeException("Error al obtener la materia: "+ e.getMessage());
        }
    }

    //Registrar una materia
    public String save(subjectsDTO subjectsDTO){
        if(subjectsDTO == null || !isValidSubject(subjectsDTO)){
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos de la materia son invalidos";
        }

        try{
            subjects subject = converToModel(subjectsDTO);
            isubjects.save(subject);
            return HttpStatus.OK.toString() + ": Materia registrada exitosamente";
        }catch(Exception e){
            return HttpStatus.INTERNAL_SERVER_ERROR.toString()+" Error al registrar la materia, Detalle: "+ e.getMessage();
        }
    }

    //Actualizar 
}