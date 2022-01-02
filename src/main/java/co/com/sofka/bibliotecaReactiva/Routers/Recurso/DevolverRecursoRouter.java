package co.com.sofka.bibliotecaReactiva.Routers.Recurso;

import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.UseCaseDevolverRecurso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class DevolverRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> devolverRecurso(UseCaseDevolverRecurso useCaseDevolverRecurso) {
        return route(
                PUT("/recursos/devolver/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseDevolverRecurso.get(request.pathVariable("id")), String.class))
        );
    }
}