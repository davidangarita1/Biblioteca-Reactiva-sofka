package co.com.sofka.bibliotecaReactiva.UseCases;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import reactor.core.publisher.Flux;

public interface ObtenerPorString {
    Flux<RecursoDTO> get(String string);
}
