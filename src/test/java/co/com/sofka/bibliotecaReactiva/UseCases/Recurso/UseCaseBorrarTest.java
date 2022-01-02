package co.com.sofka.bibliotecaReactiva.UseCases.Recurso;

import co.com.sofka.bibliotecaReactiva.DTOs.RecursoDTO;
import co.com.sofka.bibliotecaReactiva.Repositories.RepositorioRecurso;
import co.com.sofka.bibliotecaReactiva.Utils.Area;
import co.com.sofka.bibliotecaReactiva.Utils.Tipo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UseCaseBorrarTest {
    @MockBean
    private RepositorioRecurso repositorioRecurso;
    @SpyBean
    private UseCaseBorrar useCase;

    @Test
    void borrarRecurso(){
        var recursoDTO = new RecursoDTO("1", Tipo.LIBRO, true, Area.LITERATURA, "Harry Potter", LocalDate.now());

        Mockito.when(repositorioRecurso.deleteById("1")).thenReturn(Mono.empty());

        var result = useCase.deleteById("1").block();
        Assertions.assertEquals(result,null);
    }
}