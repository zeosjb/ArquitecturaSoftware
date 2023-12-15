package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;

import java.time.Instant;

public class Pago extends BaseModel{

    @NotNull
    private Instant pago;

    @NotNull
    private Integer monto;

    public Pago(Instant pago, Integer monto) {
        this.pago = pago;
        this.monto = monto;
    }

    public Instant getPago() {
        return pago;
    }

    public Integer getMonto() {
        return monto;
    }
}
