package com.co.castano.usuarios.controller;

import com.co.castano.usuarios.entity.Alumno;
import com.co.castano.usuarios.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    AlumnoService service;

    @GetMapping
    public ResponseEntity<?> listarAlumno(){
        return ResponseEntity.ok().body(service.findAll()); // ResponseEntity.ok para indicar un estado 200, y body para devolver el cuerpo de la solicitud que se llama al metodo fiindAll de la interfaz de AlumnoService
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> ver(@PathVariable Long id){
        Optional< Alumno> ob = service.findById(id);

        if(ob.isEmpty()){  //si esta vacio
            return ResponseEntity.noContent().build(); //respuesta a solicitud exitosa pero no hay contenido para enviar en respuesta, no devuelve datos o no se encuentra, build es para construir la respuesta sin un cuerpo
        }
        return ResponseEntity.ok().body(ob.get()); //metodo get obtiene el objeto alumno
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Alumno alumno){
        Alumno alumnoDb = service.save(alumno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alumnoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Alumno alumno,@PathVariable Long id){
        Optional<Alumno> ob= service.findById(id);

        if (ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        Alumno alumnoBD = ob.get(); //objeto recuperado de la bd
        alumnoBD.setNombre(alumno.getNombre()); // alumnoBD.setNombre actualiza valor del atributo que va a leerse de alumno que nos pasan en el cuerpo de solicitud que es getNombre para devovler el nombre
        alumnoBD.setApellido(alumno.getApellido());
        alumnoBD.setEmail(alumno.getEmail());

        Alumno updatedAlumno = service.save(alumnoBD); // Guarda los cambios

        return ResponseEntity.ok().body(updatedAlumno); // Retorna el alumno actualizado
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<?> eliminar(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
