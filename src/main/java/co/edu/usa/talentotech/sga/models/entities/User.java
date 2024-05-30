package co.edu.usa.talentotech.sga.models.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import co.edu.usa.talentotech.sga.models.Datos;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 * Created on May 29, 2024
 * @author fdomoreno
 */
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity
//@Document(collection = "usuario")
@JsonIncludeProperties(value = {
        "id",
        "nombre",
        "correo",
        "estado",
        "roles"
})
public class User extends Datos<User> implements Serializable {

    private static final long serialVersionUID = 9L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;
    @Column(name = "nombre_usuario", length = 100, nullable = false)
    private String nombreUsuario;
    @Column(name = "contrasena", length = 100, nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String contrasena;
    @Column(name = "encription_key")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String encrytionKey;
    @Column(name = "correo", length = 100, nullable = false)
    private String correo;
    @Column(name="client_id")
    private String clientId;
    @Column(name = "client_secret", nullable = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String clientSecret;
    /*private Estado estado;
    private List<Rol> roles;*/
}