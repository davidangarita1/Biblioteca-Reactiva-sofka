package co.com.sofka.bibliotecaReactiva.Routers.Recurso;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.Recurso.UseCaseActualizar;
import co.com.sofka.bibliotecaReactiva.Utils.Area;
import co.com.sofka.bibliotecaReactiva.Utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ActualizarRecursoRouter.class, UseCaseActualizar.class, RecursoMapper.class})
class ActualizarRecursoRouterTest {
    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void ActualizarRecursoTest(){
        Recurso recurso = new Recurso();
        recurso.setId("12345");
        recurso.setArea(Area.FANTASIA);
        recurso.setDisponible(true);
        recurso.setTipo(Tipo.LIBRO);
        recurso.setNombre("Harry Potter");
        recurso.setFecha(LocalDate.now());

        RecursoDTO recursoDTO = new RecursoDTO(recurso.getId(),
                recurso.getTipo(),
                recurso.isDisponible(),
                recurso.getArea(),
                recurso.getNombre(),
                recurso.getFecha());

        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.put()
                .uri("/recursos/actualizar")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getArea()).isEqualTo(recurso.getArea());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso.getTipo());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso.isDisponible());
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso.getId());
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).save(any());

    }
}