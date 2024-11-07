package com.co.castano.usuarios.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY: indica que la bd es responsable de generar el valor de la clave primaria id al insertar un nuevo registro en la bd
    private Long Id;
    private String nombre;
    private String apellido;
    private String email;

    /**
     * @Column: create_at es el nombre de columna que almacena fecha y hora en que se crea un registro de alumno
     * @Temporal: indica que el atributo de tipo date debe mapearse a un tipo de dato de fecha/hora en la bd, y
     * temporaltype.TIMESTAMP especifica que el atributo debe mapearse como un timestamp, lo que signifi9ca que almacenara tanto la fecha como
     * la hora con milisegundo, se usa para registrar cuando ocurrio un evento
     * */
    @Column(name="create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    /**
     * Descripcion: cada que se guarde un nuevo alumno se almacenará la fecha de creación de ese alumno
     * @PrePersist: anotación usada para marcar un metodo en una clase o entidad que debe ejecutatse antes
     * de que la entidad se inserte en la bd, se usa para realizar acciones antes de que el objeto se inserte en la bd
     * */
    @PrePersist
    private void prePersiste(){
        this.createAt = new Date();
    }

    public Alumno(Long id, String nombre, String apellido, String email, Date createAt) {
        Id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createAt = createAt;
    }

    public Alumno() {

    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", createAt=" + createAt +
                '}';
    }
}
