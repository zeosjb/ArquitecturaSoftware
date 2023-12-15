package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Pago;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * System Operations
 *
 * @author Arquitecturas de Software
 */
public interface Sistema {

    /**
     * Agrega un edificio al sistema.
     *
     * @param edificio que se agrega
     * @return El edificio agregado
     * @throws SistemaException Si es que existe un error, informa sobre este.
     */
    Edificio add(Edificio edificio) throws SistemaException;

    /**
     * Agrega una persona al sistema
     *
     * @param persona que se agrega
     * @return La persona agregada
     * @throws SistemaException Si es que existe un error, informa sobre este.
     */
    Persona add(Persona persona) throws SistemaException;

    /**
     * Agrega un departamento al sistema.
     *
     * @param departamento que se agrega
     * @return El departamento agregado
     * @throws SistemaException Si es que existe un error, informa sobre este.
     */
    Departamento add(Departamento departamento) throws SistemaException;

    /**
     * Se realiza un contrato de una persona al contratar un departamento con una fecha y un pago especifico
     *
     * @param duenio La persona quien se "arrienda" el departamento
     * @param departamento Departamento que es arrendado
     * @param fechaPago La fecha de pago del contrato de arriendo
     * @return El contrato con la informacion
     * @throws SistemaException si es que existe un error, informa sobre este.
     */
    Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) throws SistemaException;

    /**
     * Se obtiene la lista de todos los contratos del sistema.
     *
     * @return La lista de todos los contratos
     */
    List<Contrato> getContratos();

    /**
     * Se obtiene la lsita de todas las personas del sistema
     *
     * @return La lista de personas
     */
    List<Persona> getPersonas();

    /**
     * Se obtiene la lista de los pagos realizados asociados al rut de la persona.
     *
     * @param rut de la persona.
     * @return La lista de pagos realizados por una persona
     */
    List<Pago> getPagos(String rut);
    Optional<Persona> getPersona(String rut);

    void populate();
}
