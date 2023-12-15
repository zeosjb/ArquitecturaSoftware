package cl.ucn.disc.as.ui;

import cl.ucn.disc.as.model.exceptions.IllegalDomainException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.javalin.Javalin;
import io.javalin.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Instant;

@Slf4j
public class ApiRestServer {
    private ApiRestServer() {

    }

    private static Gson createAndConfigureGson() {
        TypeAdapter<Instant> instantTypeAdapter = new TypeAdapter<>() {
            @Override
            public void write(JsonWriter out, Instant value) throws IOException {
                if (value == null) {
                    out.nullValue();
                } else {
                    out.value(value.toEpochMilli());
                }
            }

            @Override
            public Instant read(JsonReader in) throws IOException {
                if (in.peek() == JsonToken.NULL) {
                    in.nextNull();
                    return null;
                }
                return Instant.ofEpochMilli(in.nextLong());
            }
        };
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Instant.class, instantTypeAdapter)
                .create();
    }

    private static Javalin createAndConfigureJavalin() {
        JsonMapper jsonMapper = new JsonMapper() {
            private static final Gson GSON = createAndConfigureGson();

            @NotNull
            @Override
            public <T> T fromJsonString(@NotNull String json, @NotNull Type targetType) {
                return GSON.fromJson(json, targetType);
            }

            @NotNull
            @Override
            public String toJsonString(@NotNull Object object, @NotNull Type type) {
                return GSON.toJson(object, type);
            }
        };
        return  Javalin.create(config -> {
            config.jsonMapper(jsonMapper);
            config.compression.gzipOnly(9);
            config.requestLogger.http((ctx, ms) -> {
                log.debug("Served: {} in {} ms.", ctx.fullUrl(), ms);
            });
            config.plugins.enableDevLogging();
        });
    }

    public static Javalin start(final Integer port, final RoutesConfigurator routesConfigurator) {
        if (port < 1024 || port > 65535) {
            log.error("Bad port: {}", port);
            throw new IllegalDomainException("Bad port: " + port);
        }
        log.debug("Starting ApiRestServer on port: {}", port);

        Javalin app = createAndConfigureJavalin();

        routesConfigurator.configure(app);

        Runtime.getRuntime().addShutdownHook(new Thread((app::stop)));

        app.events(event -> {
            event.serverStarting(() -> {
                log.debug("ApiRestServer starting...");
            });
            event.serverStarted(() -> {
                log.debug("ApiRestServer started.");
            });
            event.serverStopping(() -> {
                log.debug("ApiRestServer stopping...");
            });
            event.serverStopped(() -> {
                log.debug("ApiRestServer started.");
            });
        });
        return app.start(port);
    }
}
