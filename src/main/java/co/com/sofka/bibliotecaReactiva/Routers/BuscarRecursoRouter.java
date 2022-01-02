package co.com.sofka.bibliotecaReactiva.Routers;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.UseCaseBuscar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BuscarRecursoRouter {
    @Bean
    public RouterFunction<ServerResponse> consultarRecursos(UseCaseBuscar useCase) {
        return route(GET("/recursos/buscar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(useCase.findById(request.pathVariable("id")), RecursoDTO.class));
    }
}
