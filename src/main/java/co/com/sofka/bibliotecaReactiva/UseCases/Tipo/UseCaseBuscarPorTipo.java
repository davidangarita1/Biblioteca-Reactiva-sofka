package co.com.sofka.bibliotecaReactiva.UseCases.Tipo;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.ObtenerPorString;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class UseCaseBuscarPorTipo implements ObtenerPorString {
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseBuscarPorTipo(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Flux<RecursoDTO> get(String tipo) {
        return repositorio.findByTipo(tipo).map(mapper.mapRecursoToDTO());
    }
}
