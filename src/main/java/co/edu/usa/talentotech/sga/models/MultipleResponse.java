package co.edu.usa.talentotech.sga.models;

import java.util.List;
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
@AllArgsConstructor
@NoArgsConstructor
public class MultipleResponse extends Response implements java.io.Serializable {

    private static final long serialVersionUID = 2L;

    private List<? extends Datos> data;
    private Respuesta respuesta = new Respuesta();

}
