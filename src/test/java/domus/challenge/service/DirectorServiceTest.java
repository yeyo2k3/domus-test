package domus.challenge.service;

import domus.challenge.api.ApiDataMoviesResponse;
import domus.challenge.client.MoviesClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DirectorServiceTest {

    @Mock
    private MoviesClient moviesClient;

    @InjectMocks
    private DirectorService directorService;

    @Test
    void getDirector() {
        List<ApiDataMoviesResponse> movies = List.of(
                ApiDataMoviesResponse.builder().title("Title1").director("Martin Scorsese").build(),
                ApiDataMoviesResponse.builder().title("Title2").director("Martin Scorsese").build(),
                ApiDataMoviesResponse.builder().title("Title3").director("Woody Allen").build()
                );
        Mockito.when(moviesClient.fetchAllMovies()).thenReturn(Flux.fromIterable(movies));
        Mono<Map<String, List<String>>> result = directorService.getDirectors(2L);

        StepVerifier.create(result)
                .assertNext(map -> {
                    List<String> directors = map.get("directors");
                    assertEquals(1, directors.size());
                    assertTrue(directors.contains("Martin Scorsese"));
                })
                .verifyComplete();
    }

    @Test
    void getDirectorsAboveThreshold_returnsEmptyWhenNoDirectorAboveThreshold() {
        List<ApiDataMoviesResponse> movies = List.of(
                ApiDataMoviesResponse.builder().title("Title1").director("Director1").build(),
                ApiDataMoviesResponse.builder().title("Title2").director("Director2").build()
        );

        Mockito.when(moviesClient.fetchAllMovies()).thenReturn(Flux.fromIterable(movies));

        Mono<Map<String, List<String>>> resultMono = directorService.getDirectors(10L);

        StepVerifier.create(resultMono)
                .assertNext(map -> {
                    assertNotNull(map);
                    assertTrue(map.get("directors").isEmpty());
                })
                .verifyComplete();
    }
}
