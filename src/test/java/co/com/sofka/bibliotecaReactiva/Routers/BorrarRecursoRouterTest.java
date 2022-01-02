package co.com.sofka.bibliotecaReactiva.Routers;

import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.Routers.Recurso.BorrarRecursoRouter;
import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.UseCaseBorrar;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BorrarRecursoRouter.class, UseCaseBorrar.class, RecursoMapper.class})
class BorrarRecursoRouterTest {
    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private UseCaseBorrar useCaseBorrar;

    @Test
    void testBorrarRecurso() {
        when(repositorio.deleteById("1")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/recursos/borrar/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}