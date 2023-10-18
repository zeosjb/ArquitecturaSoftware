package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import jakarta.servlet.http.PushBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Debug;
import org.jetbrains.annotations.NotNull;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;

/**
 * Sistema Implementation.
 *
 * @author Arquitectura de Software
 */
@Slf4j
public class SistemaImpl implements Sistema{

    /**
     * The Database
     */
    private final Database database;


    /**
     * {@inheritDoc}
     * @param edificio
     *
     */
    @Override
    public Edificio add(@NotNull Edificio edificio){
        try {
            this.database.save(edificio);
        } catch (PersistenceException ex) {
            //TODO: save the exception
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
        // WARN: need to retrieve the id?
        return edificio;
    }

    @Override
    public Departamento add(@NotNull Departamento departamento, Edificio edificio){
        edificio.add(departamento);
        try{
            this.database.save(departamento);
            this.database.save(edificio);
        } catch (PersistenceException ex){
            log.error("Error", ex);
            throw new SistemaException("Error al agregar un departamento a un edificio", ex);
        }
        return departamento;
    }

    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        Contrato contrato = new Contrato(duenio, departamento, fechaPago);
        try {
            this.database.save(contrato);
        } catch (PersistenceException ex) {
            log.error("Error", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }
        return contrato;
    }

    @Override
    public List<Contrato> getContratos(){
        return database.find(Contrato.class).findPagedList();
    }

    @Override
    public List<Persona> getPersonas(){
        return database.find(Persona.class).findPagedList();
    }

    @Override
    public List<Pago> getPagos(String rut){
        return database.find(Pago.class).where().eq("persona.rut", rut).findPagedList();
    }
}
