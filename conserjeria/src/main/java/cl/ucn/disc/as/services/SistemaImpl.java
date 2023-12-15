package cl.ucn.disc.as.services;

import cl.ucn.disc.as.exceptions.SistemaException;
import cl.ucn.disc.as.model.Contrato;
import cl.ucn.disc.as.model.Departamento;
import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.model.Pago;
import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValues;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import io.ebean.Database;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.PersistenceException;
import java.time.Instant;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

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

    public SistemaImpl(Database database) {
        this.database = database;
    }


    /**
     * {@inheritDoc}
     * @param edificio
     *
     */
    @Override
    public Edificio add( Edificio edificio){
        try {
            this.database.save(edificio);
            return edificio;
        } catch (PersistenceException ex) {
            log.error("Error al agregar el edificio", ex);
            throw new SistemaException("Error al agregar un edificio", ex);
        }
    }

    @Override
    public Persona add(Persona persona){
        try {
            this.database.save(persona);
            return persona;
        } catch (PersistenceException ex) {
            log.error("Error al agregar la persona", ex);
            throw new SistemaException("Error al agregar una persona", ex);
        }
    }

    @Override
    public Departamento add(Departamento departamento){
        try{
            this.database.save(departamento);
            return departamento;
        } catch (PersistenceException ex){
            log.error("Error al agregar el departamento", ex);
            throw new SistemaException("Error al agregar un departamento", ex);
        }
    }

    @Override
    public Contrato realizarContrato(Persona duenio, Departamento departamento, Instant fechaPago) {
        try {
            Contrato contrato = new Contrato(duenio, departamento, fechaPago);
            this.database.save(contrato);
            return contrato;
        } catch (PersistenceException ex) {
            log.error("Error al realizar el contrato", ex);
            throw new SistemaException("Error al realizar un contrato", ex);
        }
    }

    @Override
    public List<Contrato> getContratos(){
        List<Contrato> contratos = database.find(Contrato.class).findList();
        return contratos;
    }

    @Override
    public List<Persona> getPersonas(){
        List<Persona> personas = database.find(Persona.class).findList();
        return personas;
    }

    @Override
    public List<Pago> getPagos(String rut){
        List<Pago> pagos = database.find(Pago.class).where().eq("rut", rut).findList();
        return pagos;
    }

    public Optional<Persona> getPersona(String rut){
        Persona persona = database.find(Persona.class).where().eq("rut", rut).findOne();
        return Optional.ofNullable(persona);
    }

    @Override
    public  void populate(){
        {
            Persona persona = Persona.builder()
                    .rut("20260521-4")
                    .nombre("Jose")
                    .apellidos("Benitez Rojas")
                    .email("jose.benitez@alumnos.ucn.cl")
                    .telefono("123456789")
                    .build();
            this.database.save(persona);
        }

        Locale locale = new Locale("es-CL");
        FakeValuesService fvs = new FakeValuesService(locale, new RandomService());
        Faker faker = new Faker(locale);

        for (int i = 0; i < 1000; i++) {
            Persona persona = Persona.builder()
                    .rut(fvs.bothify("#######-#"))
                    .nombre(faker.name().firstName())
                    .apellidos(faker.name().lastName())
                    .email(fvs.bothify("???###@gmail.com"))
                    .telefono(fvs.bothify("+569########"))
                    .build();
            this.database.save(persona);
        }
    }
}
