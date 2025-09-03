package ar.edu.utn.dds.k3003.app;

import ar.edu.utn.dds.k3003.clients.HechosProxy;
import ar.edu.utn.dds.k3003.facades.dtos.Constants;
import ar.edu.utn.dds.k3003.facades.dtos.HechoDTO;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class WebApp {
    public static void main(String[] args) {

        var env = System.getenv();
        var port = Integer.parseInt(env.getOrDefault("PORT", "8080"));
        var app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson().updateMapper(mapper -> {
                configureObjectMapper(mapper);
            }));
        }).start(port);
        //ObjectMapper objectMapper = WebApp.createObjectMapper();

        // Inicializar el proxy con la URL de tu compañero
        //HechosProxy proxy = new HechosProxy(objectMapper);

        //try {
            // Hacer la llamada al endpoint de prueba
            //HechoDTO hecho = proxy.buscarHechoXId("1");

            // Mostrar el resultado en consola
            //System.out.println("Hecho recibido: " + hecho);

        //} catch (Exception e) {
        //    e.printStackTrace();
        // }

        /*

        var objectMapper = createObjectMapper();
        var fachada = new Fachada();
        fachada.setHechosProxy( new HechosProxy(objectMapper));





        try {
            fachada.validarHechoAsociado("1");
            //System.out.println("Hecho obtenido: " + hecho);
        } catch (Exception e) {
            e.printStackTrace();
        }

        app.get("/hecho/:id", ctx -> {
            String id = ctx.pathParam("id");
            HechoDTO hecho = fachada.validarHechoAsociado(id);
            ctx.json(hecho);
        });

 */
    }

    public static ObjectMapper createObjectMapper() {
        var objectMapper = new ObjectMapper();
        configureObjectMapper(objectMapper);
        return objectMapper;
    }

    public static void configureObjectMapper(ObjectMapper objectMapper) {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        var sdf = new SimpleDateFormat(Constants.DEFAULT_SERIALIZATION_FORMAT, Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        objectMapper.setDateFormat(sdf);
    }
}
