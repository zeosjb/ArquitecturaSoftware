package cl.ucn.disc.as.grpc;

import cl.ucn.disc.as.grpc.PersonaGrpcServiceGrpc;
import cl.ucn.disc.as.services.Sistema;
import lombok.extern.slf4j.Slf4j;
import io.grpc.stub.StreamObserver;
import java.util.Optional;

@Slf4j
public final class PersonaGrpcServiceImpl extends PersonaGrpcServiceGrpc.PersonaGrpcServiceImplBase {
    /**
     *
     *
     * @param request
     * @param responseObserver
     */
    @Override
    public void retrieve(PersonaGrpcRequest request, StreamObserver<PersonaGrpcResponse> responseObserver) {
        log.debug("Retrieving Persona with RUT: {}", request.getRut());
        PersonaGrpc personaGrpc = PersonaGrpc.newBuilder()
                .setRut(request.getRut())
                .setNombre("Jose")
                .setApellidos("Benitez Rojas")
                .setEmail("jose.benitez@alumnos.ucn.cl")
                .setTelefono("+56988313356")
                .build();
        responseObserver.onNext(PersonaGrpcResponse.newBuilder()
                .setPersona(personaGrpc)
                .build());
        responseObserver.onCompleted();
    }
}
