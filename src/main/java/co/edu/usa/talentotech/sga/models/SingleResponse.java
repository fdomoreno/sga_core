package co.edu.usa.talentotech.sga.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/*
 * Created on May 03, 2024
 * @author fdomoreno
 */
@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@AllArgsConstructor
public class SingleResponse extends Response implements Serializable {

    private static final long serialVersionUID = 3L;

    private Datos data;
    private Respuesta respuesta = new Respuesta();

}
