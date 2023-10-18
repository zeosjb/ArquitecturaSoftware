package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;

/**
 *  Departamento class.
 *
 * @author ZeosBJ
 */
@ToString
@AllArgsConstructor
@Builder
@Entity
public class Departamento extends BaseModel{

    @Getter
    @NotNull
    private Integer numero;

    @Getter
    @NotNull
    private Integer piso;

    public static class DepartamentoBuilder{
        public Departamento build(){
            return new Departamento(
                    this.numero,
                    this.piso
            );
        }
    }
}
