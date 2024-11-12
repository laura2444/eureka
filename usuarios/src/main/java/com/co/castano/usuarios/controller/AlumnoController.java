package com.co.castano.usuarios.controller;

import com.co.castano.usuarios.entity.Alumno;
import com.co.castano.usuarios.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class AlumnoController {

    @Autowired
    AlumnoService service;

    // Inyectar la variable de entorno 'BALANCEADOR_TEST' en el controlador
    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    /* BALANCEADOR TEST

    Un balanceador de carga es un componente que distribuye de forma equitativa el tráfico de red o las solicitudes entre múltiples instancias de un servicio o servidor


    Las múltiples instancias de un servicio son copias independientes del mismo servicio (o aplicación) que se ejecutan en paralelo en diferentes entornos
    (como servidores, máquinas virtuales, o contenedores). Cada instancia es capaz de atender solicitudes de manera independiente, pero todas funcionan juntas
    para aumentar la capacidad y disponibilidad del sistema en general.


    el balanceador test utiliza una variable inyectada (config.balanceador.test) en el controlador para mostrar qué instancia específica del servicio está respondiendo
    a cada solicitud. Esto permite validar que el balanceador de carga distribuye correctamente las solicitudes entre distintas instancias.

    Por ejemplo, si tienes dos instancias del servicio "usuarios" y ejecutas repetidamente el endpoint /balanceador-test, deberías ver que la respuesta alterna entre las dos
     instancias, indicando que el balanceador está funcionando
     *
    * */

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest(){
        Map<String, Object> response= new HashMap<String, Object>();
        response.put("balanceador",balanceadorTest); //variable que identifica de qué instancia específica de tu servicio "usuarios" proviene la respuesta, permitiéndote saber qué instancia está siendo utilizada en ese momento a través del balanceador de carga.
        response.put("alumno",service.findAll());//Agregamos la lista de alumnos

        return ResponseEntity.ok().body(response); // Retornamos la respuesta HTTP 200 con el body
    }


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
