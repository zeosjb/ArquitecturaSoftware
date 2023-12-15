package cl.ucn.disc.as;

import cl.ucn.disc.as.ui.ApiRestServer;
import cl.ucn.disc.as.ui.WebController;
import lombok.extern.slf4j.Slf4j;
import io.javalin.Javalin;
import cl.ucn.disc.as.grpc.PersonaGrpcServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

@Slf4j
public final class Main {
    /**
     *  The Main.
     *
     * @param args to use.
     * */
    public static void main(String[] args) throws IOException, InterruptedException {

        log.debug("Starting the main ..");

        log.debug("Library path : {}", System.getProperty("java.library.path"));


        //Start the API Rest Server
        Javalin app = ApiRestServer.start(7070,new WebController());

        log.debug("Starting Grpc server");
        Server server = ServerBuilder
                .forPort(50123)
                .addService(new PersonaGrpcServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::shutdown));
        server.awaitTermination();
        log.debug("Sistema deteniendose...");

        app.stop();

        log.debug("Sistema finalizado...");
    }
}
