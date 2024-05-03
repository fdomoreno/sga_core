package co.edu.usa.talentotech.sga.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/*
 * Created on May 03, 2024
 * @author fdomoreno
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(value = {
    "stackTrace",
    "message",
    "suppressed",
    "localizedMessage",
    "cause"
})
public class Respuesta extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigo;
    private String mensaje;
    private LocalDateTime timestamp;

    public Respuesta(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public Respuesta(String codigo, String mensaje, Throwable cause) {
        super(mensaje, cause);
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.timestamp = LocalDateTime.now();
    }

    public Respuesta(String codigo, Throwable cause) {
        super(cause);
        this.codigo = codigo;
        this.timestamp = LocalDateTime.now();
    }

    public Respuesta() {
        super();
        this.timestamp = LocalDateTime.now();
    }

    public Respuesta(Throwable cause) {
        super(cause);
        this.timestamp = LocalDateTime.now();
    }

    public Respuesta(String mensaje) {
        super(mensaje);
        this.timestamp = LocalDateTime.now();
    }
}
