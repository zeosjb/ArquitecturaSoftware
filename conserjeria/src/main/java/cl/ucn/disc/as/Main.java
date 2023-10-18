package cl.ucn.disc.as;

import cl.ucn.disc.as.model.Edificio;
import cl.ucn.disc.as.model.Persona;
import cl.ucn.disc.as.services.Sistema;
import cl.ucn.disc.as.services.SistemaImpl;
import io.ebean.DB;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class Main {
    /**
     *  The Main.
     *
     * @param args to use.
     * */
    public static void main(String[] args){

        log.debug("Starting the main ..");

        Database db = DB.getDefault();

        Sistema sistema = new SistemaImpl(db);

        Edificio edificio = Edificio.builder()
                .nombre("Y1")
                .direccion("Angamos #0610 (al lado de la virgencita)")
                .build();
        log.debug("Edificio before db: {}", edificio);

        edificio = sistema.add(edificio);
        log.debug("Edificio after db: {}", edificio);

        Persona persona = Persona.builder()
                .rut(999999)
                .nombre("Diego")
                .apellidos("Urrutia Astorga")
                .email("durrutia@ucn.cl")
                .telefono("+5622355166")
                .build();

        db.save(persona);

        log.debug("The Persona before db: ${}", persona);

        log.debug("Done. :)");
    }
}
