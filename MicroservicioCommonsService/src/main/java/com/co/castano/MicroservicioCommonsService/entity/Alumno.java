package com.co.castano.MicroservicioCommonsService.entity;

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

    //El constructor vacío es necesario porque JPA (a través de Hibernate) utiliza técnicas como la reflexión para crear instancias de las entidades cuando interactúa con la base de datos (por ejemplo, al recuperar datos).
    //Si no existe un constructor vacío, JPA lanzará una excepción al intentar instanciar la entidad. Esto se debe a que necesita crear una instancia sin parámetros y luego llenar sus propiedades con los valores obtenidos de la base de datos
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
    /**
     * @equals: El método equals(Object obj) en Java se utiliza para comparar dos objetos y determinar si son "iguales". Por defecto, la implementación del método equals en la clase Object (de la cual heredan todas las clases en Java)
     * compara las referencias de memoria de los objetos, es decir, verifica si los dos objetos son exactamente la misma instancia.
     *
     * */

    @Override
    public boolean equals(Object obj) {
        // Verificar si son el mismo objeto en memoria
        if (this == obj) {
            return true;  // Si ambos apuntan al mismo objeto, son iguales
        }

        // Verificar si el objeto es una instancia de la clase Alumno
        if (!(obj instanceof Alumno)) {
            return false;  // Si el objeto no es una instancia de Alumno, no son iguales
        }


        Alumno a = (Alumno) obj;  // cast, Convertir el objeto obj a un objeto Alumno

        // Comparar Id
        return this.Id != null && this.Id.equals(a.getId());  // Si el Id no es null, comparamos los Ids
    }

}
