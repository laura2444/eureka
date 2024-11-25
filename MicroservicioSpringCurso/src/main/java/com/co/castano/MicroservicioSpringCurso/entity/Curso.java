package com.co.castano.MicroservicioSpringCurso.entity;

import com.co.castano.MicroservicioCommonsService.entity.Alumno;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY: indica que la bd es responsable de generar el valor de la clave primaria id al insertar un nuevo registro en la bd

    private Long id;
    private String nombre;

    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @PrePersist
    private void prePersiste(){
        this.createAt = new Date();
    }

    @OneToMany(fetch= FetchType.LAZY) // recuperacion de datos, estos solo se cargaran cuando se acceda a la lista de almnos, sino no se cargan, esto por decir que es LAZY, y este NO obtiene el total de los objetos de la relacion, solo una parte
    private List<Alumno> listaAlumno;

    public Curso(Long id, String nombre, Date createAt) {
        this.id = id;
        this.nombre = nombre;
        this.createAt = createAt;
        this.listaAlumno= new ArrayList<>();

    }

    public Curso(){

    }

    public List<Alumno> getListaAlumno() {
        return listaAlumno;
    }

    public void setListaAlumno(List<Alumno> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    public void addAlumnos(Alumno alumno){
        this.listaAlumno.add(alumno);
    }

    public void removeAlumnos(Alumno alumno){
        this.listaAlumno.remove(alumno);
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", createAt=" + createAt +
                ", listaAlumno=" + listaAlumno +
                '}';
    }
}



