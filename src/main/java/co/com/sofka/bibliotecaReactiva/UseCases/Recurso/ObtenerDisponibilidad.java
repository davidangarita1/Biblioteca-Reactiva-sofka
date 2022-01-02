package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import reactor.core.publisher.Mono;

public interface ObtenerDisponibilidad {
    Mono<String> get(String id);
}
