package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Instant;
import java.util.List;

/**
 * Class Contrato.
 *
 * author Arquitectura de Software
 */
@ToString
@AllArgsConstructor
@Builder
@Entity
public class Contrato extends BaseModel {

    @NotNull
    private Instant fechaPago;

    @NotNull
    private Persona persona;

    @NotNull
    private Departamento departamento;

    @NotNull
    private List<Pago> pagos;

    public Contrato(Persona persona, Departamento departamento, Instant fechaPago) {
        this.persona = persona;
        this.departamento = departamento;
        this.fechaPago = fechaPago;
    }

    public Instant getFechaPago() {
        return fechaPago;
    }

    public Persona getPersona() {
        return persona;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public List<Pago> getPagos() {
        return pagos;
    }
}
