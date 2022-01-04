package co.com.sofka.bibliotecaReactiva.Routers.Recurso;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.UseCaseDevolverRecurso;
import co.com.sofka.bibliotecaReactiva.Utils.Area;
import co.com.sofka.bibliotecaReactiva.Utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DevolverRecursoRouter.class, UseCaseDevolverRecurso.class, RecursoMapper.class})
class DevolverRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void DevolverRecursoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("12345");
        recurso1.setArea(Area.FANTASIA);
        recurso1.setDisponible(false);
        recurso1.setTipo(Tipo.LIBRO);
        recurso1.setNombre("Harry Potter");
        recurso1.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/devolver/12345")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("El recurso fue prestado con exito"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("12345");
    }

    @Test
    public void DevolverRecursoNoValidoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("12345");
        recurso1.setArea(Area.FANTASIA);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.LIBRO);
        recurso1.setNombre("Harry Potter");
        recurso1.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/devolver/12345")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("El recurso no está prestado"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("12345");
    }

}