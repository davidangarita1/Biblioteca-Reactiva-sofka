package co.com.sofka.bibliotecaReactiva.Routers;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.UseCases.UseCaseBuscarPorAreaYTipo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BuscarPorAreaYTipoRouter {
    @Bean
    public RouterFunction<ServerResponse> buscarPorAreaYTipo(UseCaseBuscarPorAreaYTipo useCaseBuscarPorAreaYTipo) {
        return route(
                GET("/recursos/filtrar/{area}/{tipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseBuscarPorAreaYTipo.get(request.pathVariable("area"), request.pathVariable("tipo")), RecursoDTO.class))
        );
    }
}
