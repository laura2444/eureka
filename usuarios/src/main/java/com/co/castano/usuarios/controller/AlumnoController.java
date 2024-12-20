package com.co.castano.usuarios.controller;

import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import com.co.castano.MicroservicioSpringCommons.controller.CommonController;
import com.co.castano.usuarios.service.AlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController extends CommonController<Alumno, AlumnoService> {

    @Autowired
    private AlumnoService service;

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

    //se usa el super en el constructor de alumno controller para llamar a la libreria generica a la clase de common controller para hacer el tema del crud, ademas se le pasa como argumento el service de alumnos, ya que como sabemos en la clase genercia de controller se usa siempre super.findid etc... ya que exttiende de interfaz de CRUD
    //el problema es que la clase generica no sabe que service usar, por eso aqui le decimos que use el service de alumnos, ESTO ES ESCENCIAL PARA QUE FUNCIONE LA LIBRERIA GENERICA
    public AlumnoController(AlumnoService service){
        super(service);
    }

    @GetMapping("/balanceador-test")
    public ResponseEntity<?> balanceadorTest(){
        Map<String, Object> response= new HashMap<String, Object>();
        response.put("balanceador",balanceadorTest); //variable que identifica de qué instancia específica de tu servicio "usuarios" proviene la respuesta, permitiéndote saber qué instancia está siendo utilizada en ese momento a través del balanceador de carga.
        response.put("alumno",service.findAll());//Agregamos la lista de alumnos

        return ResponseEntity.ok().body(response); // Retornamos la respuesta HTTP 200 con el body
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

}
