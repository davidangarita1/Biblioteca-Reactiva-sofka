package co.com.sofka.bibliotecaReactiva.Routers;

import co.com.sofka.bibliotecaReactiva.Collections.Recurso;
import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.Mappers.RecursoMapper;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.UseCases.UseCaseBuscarPorAreaYTipo;
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
@ContextConfiguration(classes= {BuscarPorAreaYTipoRouter.class, UseCaseBuscarPorAreaYTipo.class, RecursoMapper.class})
class BuscarPorAreaYTipoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void buscarPorAreaYTipoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setArea(Area.FANTASIA);
        recurso1.setDisponible(true);
        recurso1.setTipo(Tipo.LIBRO);
        recurso1.setNombre("Harry Potter");
        recurso1.setFecha(LocalDate.now());

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setArea(Area.ARTES);
        recurso2.setDisponible(true);
        recurso2.setTipo(Tipo.DVD);
        recurso2.setNombre("Loomis");
        recurso2.setFecha(LocalDate.now());

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByAreaAndTipo(recurso1.getArea().toString(),
                recurso1.getTipo().toString()))
                .thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrar/FANTASIA/LIBRO")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getArea()).isEqualTo(recurso1.getArea());
                            Assertions.assertThat(userResponse.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.get(1).getArea()).isEqualTo(recurso2.getArea());
                            Assertions.assertThat(userResponse.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByAreaAndTipo("FANTASIA", "LIBRO");
    }

}