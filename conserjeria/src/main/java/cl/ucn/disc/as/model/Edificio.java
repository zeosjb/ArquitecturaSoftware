package cl.ucn.disc.as.model;

import io.ebean.annotation.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Edificio
 *
 * author Arquitectura de Sistemas
 */

@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class Edificio extends BaseModel{

    @Getter
    @NotNull
    private String nombre;

    @Getter
    @NotNull
    private String direccion;

    private List<Departamento> departamentos = new ArrayList<>();

    public Edificio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public void add(Departamento departamento){
        departamentos.add(departamento);
    }

    public List<Departamento> getDepartamentos(){
        return departamentos;
    }

}
