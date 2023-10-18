package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import java.time.Instant;

@ToString
@AllArgsConstructor
@Builder
@Entity
public class Pago {

    @Getter
    @NotNull
    private Instant pago;

    @Getter
    @NotNull
    private Integer monto;

    public static class PagoBuilder{
        public Pago build(){
            return new Pago(
                    this.pago,
                    this.monto
            );
        }
    }
}
