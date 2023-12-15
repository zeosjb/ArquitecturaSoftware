package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;

import java.time.Instant;

public class Pago extends BaseModel{

    @NotNull
    private Instant fechaPago;

    @NotNull
    private Integer monto;

    public Pago(Instant fechaPago, Integer monto) {
        this.fechaPago = fechaPago;
        this.monto = monto;
    }

    public Instant getPago() {
        return fechaPago;
    }

    public Integer getMonto() {
        return monto;
    }
}
