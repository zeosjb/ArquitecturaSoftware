package cl.ucn.disc.as.services;

import cl.ucn.disc.as.model.Edificio;

/**
 * System Operations
 *
 * @author Arquitecturas de Software
 */
public interface Sistema {

    /**
     * Agregar un edificio al sistema
     *
     * @param edificio a agregar
     */
    Edificio add(Edificio edificio);

    Departamento addDepartamento(Departamento departamento, Edificio edificio);

    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago);

    List<Contrato> getContratos();

    List<Persona> getPersonas();

    List<Pago> getPagos(String rut);
}
