package co.com.sofka.bibliotecaReactiva.Repositories;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findByArea(String area);
}
