package cl.ucn.disc.as.model;

import io.ebeaninternal.server.util.Str;

import java.util.List;

/**
 * Class Edificio
 *
 * author Arquitectura de Sistemas
 */

public class Edificio extends BaseModel{

    private String nombre;
    private String direccion;
    private List<Departamento> departamentos;

    public Edificio(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void addDepartamento(Departamento departamento) {
        departamentos.add(departamento);
    }

    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

}
