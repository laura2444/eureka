package com.co.castano.MicroservicioSpringCurso.controller;

import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import com.co.castano.MicroservicioSpringCommons.controller.CommonController;
import com.co.castano.MicroservicioSpringCurso.entity.Curso;
import com.co.castano.MicroservicioSpringCurso.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cursos")
public class CursoController extends CommonController<Curso, CursoService> {

    @Autowired
    private CursoService service;

    // Inyectar la variable de entorno 'BALANCEADOR_TEST' en el controlador
    @Value("${config.balanceador.test}")
    private String balanceadorTest;

    public CursoController(CursoService service) {
        super(service);
    }

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
        response.put("balanceador",balanceadorTest); //variable que identifica de qué instancia específica de tu servicio "cursos" proviene la respuesta, permitiéndote saber qué instancia está siendo utilizada en ese momento a través del balanceador de carga.
        response.put("curso",service.findAll());//Agregamos la lista de cursos

        return ResponseEntity.ok().body(response); // Retornamos la respuesta HTTP 200 con el body
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Curso curso, @PathVariable Long id){
        Optional<Curso> ob= service.findById(id);

        if (ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        Curso cursoBD = ob.get(); //objeto recuperado de la bd
        cursoBD.setNombre(curso.getNombre()); // cursoBD.setNombre actualiza valor del atributo que va a leerse de cursos que nos pasan en el cuerpo de solicitud que es getNombre para devovler el nombre

        Curso updatedAlumno = service.save(cursoBD); // Guarda los cambios

        return ResponseEntity.ok().body(updatedAlumno); // Retorna el curso actualizado
    }

    //metodo para asignar un alumno al curso
    @PutMapping("/{id}/asignar-alumno")
    public ResponseEntity<?> asignarAlumno(@RequestBody List<Alumno> alumno, @PathVariable Long id){

        Optional<Curso> ob = service.findById(id);

        if(ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        Curso cursoBd = ob.get();
        alumno.forEach(a ->{
            cursoBd.addAlumnos(a);
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoBd));
    }

    //metodo para eliminar un alumno del curso
    @PutMapping("/{id}/eliminar-alumno")
    public ResponseEntity<?> eliminarAlumno(@RequestBody List<Alumno> alumno, @PathVariable Long id){

        Optional<Curso> ob = service.findById(id);

        if(ob.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        Curso cursoBd = ob.get();

        alumno.forEach(cursoBd::removeAlumnos);

        return ResponseEntity.ok().body(service.save(cursoBd));    }

}
