package co.edu.usa.talentotech.sga.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Created on May 03, 2024
 * @author fdomoreno
 */
/*
 * Created on Sep 27, 2023
 * @author fdomoreno
 */
@JsonIgnoreProperties(value = {
    "fecha_actualizacion",
    "fecha_creacion",
    "usuario_actualizacion",
    "usuario_creacion"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public abstract class Datos<T> implements Serializable {
    @Column(name = "fecha_creacion")
    @JsonProperty("fecha_creacion")
    private Timestamp fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @JsonProperty("fecha_actualizacion")
    private Timestamp fechaActualizacion;
    @JsonIncludeProperties(value = {
        "id",
        "nombre",
        "correo"
    })
    @Column(name = "usuario_creacion")
    @JsonProperty("usuario_creacion")
    private T usuarioCreacion;
    @JsonIncludeProperties(value = {
        "id",
        "nombre",
        "correo"
    })
    @Column(name = "usuario_actualizacion")
    @JsonProperty("usuario_actualizacion")
    private T usuarioActualizacion;
}
