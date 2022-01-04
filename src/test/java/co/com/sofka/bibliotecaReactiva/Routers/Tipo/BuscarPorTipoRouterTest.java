package co.com.sofka.bibliotecaReactiva.Routers.Tipo;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.Tipo.UseCaseBuscarPorTipo;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {BuscarPorTipoRouter.class, UseCaseBuscarPorTipo.class, RecursoMapper.class})
class BuscarPorTipoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarPorAreaTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.GEOGRAFIA);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.DVD);
        recurso1.setNombre("Animal Geographic");
        recurso1.setFecha(LocalDate.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setArea(Area.FANTASIA);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.LIBRO);
        recurso2.setNombre("Harry Potter");
        recurso2.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByTipo(recurso1.getTipo().toString())).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrarTipo/DVD")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByTipo("DVD");
    }

}