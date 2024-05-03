package co.edu.usa.talentotech.sga.models;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SecondaryTable;
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
    "fechaActualizacion",
    "fechaCreacion"
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Datos<T> {
    @Column(name = "fechaCreacion")
    private Timestamp fechaCreacion;
    @Column(name = "fechaActualizacion")
    private Timestamp fechaActualizacion;
    @ManyToOne
    @JoinColumn(name = "idUsuarioCreacion")
    @JsonIncludeProperties(value = {
        "id",
        "nombre",
        "correo"
    })
    private T usuarioCreacion;
    @ManyToOne
    @JoinColumn(name = "idUsuarioActualizacion")
    @JsonIncludeProperties(value = {
        "id",
        "nombre",
        "correo"
    })
    private T usuarioActualizacion;
}
