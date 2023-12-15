package cl.ucn.disc.as.model;

public class Departamento extends BaseModel{

    private Integer numero;
    private Integer piso;

    public Departamento (Integer numero, Integer piso){
        this.numero = numero;
        this.piso = piso;
    }

    public Integer getNumero(){
        return numero;
    }

    public Integer getPiso(){
        return piso;
    }
}
