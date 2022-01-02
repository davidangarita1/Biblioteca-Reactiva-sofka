package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.ObtenerDisponibilidad;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class UseCasePrestarRecurso implements ObtenerDisponibilidad {

    private final RepositorioRecurso repositorio;
    private final RecursoMapper mapper;


    public UseCasePrestarRecurso(RepositorioRecurso repositorio, RecursoMapper mapper) {
        this.repositorio = repositorio;
        this.mapper = mapper;
    }


    @Override
    public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorio.findById(id);

        return recursoMono.flatMap(r -> {
            if (r.isDisponible()) {
                r.setDisponible(false);
                r.setFecha(LocalDate.now());
                repositorio.save(r);
                return Mono.just("El recurso fue prestado con exito");
            }
            return Mono.just("El recurso no está disponible");
        });
    }
}
