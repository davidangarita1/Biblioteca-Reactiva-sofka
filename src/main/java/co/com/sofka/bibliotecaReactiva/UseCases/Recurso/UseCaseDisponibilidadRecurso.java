package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDisponibilidadRecurso implements ObtenerDisponibilidad{
    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;

    public UseCaseDisponibilidadRecurso(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }

    @Override
    public Mono<String> get(String id) {
        return repositorio.findById(id).map(r->
                r.isDisponible() ?
                        "El recurso está disponible"
                        : "El recurso no está disponible, fue prestado el: "+ r.getFecha()
        );
    }
}
