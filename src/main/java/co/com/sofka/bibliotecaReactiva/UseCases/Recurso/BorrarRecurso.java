package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BorrarRecurso {
    Mono<Void> deleteById(String id);
}
