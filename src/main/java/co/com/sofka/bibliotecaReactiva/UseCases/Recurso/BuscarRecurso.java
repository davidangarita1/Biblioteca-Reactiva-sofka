package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface BuscarRecurso {
    Mono<RecursoDTO> findById(String id);
}
