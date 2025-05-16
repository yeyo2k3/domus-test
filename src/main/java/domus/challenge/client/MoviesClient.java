package domus.challenge.client;

import domus.challenge.api.ApiDataMoviesResponse;
import domus.challenge.api.ApiMoviesResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MoviesClient {

    private final WebClient webClient;

    public MoviesClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://challenge.iugolabs.com").build();
    }

    public Flux<ApiDataMoviesResponse> fetchAllMovies() {

        return webClient.get()
                .uri("/api/movies/search?page=1")
                .retrieve()
                .bodyToMono(ApiMoviesResponse.class)
                .flatMapMany(firstPage -> {
                    int totalPages = firstPage.getTotalPages();
                    return Flux.range(1, totalPages)
                            .flatMap(page -> webClient.get()
                                    .uri("/api/movies/search?page={page}", page)
                                    .retrieve()
                                    .bodyToMono(ApiMoviesResponse.class)
                                    .flatMapMany(response -> Flux.fromIterable(response.getMovies()))
                            );
                });
    }
}
