package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface GuardarRecurso {
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}

