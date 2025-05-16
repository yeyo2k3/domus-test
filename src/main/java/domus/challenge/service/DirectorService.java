package domus.challenge.service;

import domus.challenge.api.ApiDataMoviesResponse;
import domus.challenge.api.ApiMoviesResponse;
import domus.challenge.client.MoviesClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectorService {

    @Autowired
    private MoviesClient moviesClient;

    public Mono<Map<String,List<String>>> getDirectors(Long threshold){
        if (threshold < 0) {
            return Mono.just(Map.of("directors", List.of()));
        }

        return moviesClient.fetchAllMovies()
                .filter(movie -> movie.getDirector() != null && !movie.getDirector().isBlank())
                .collect(Collectors.groupingBy(ApiDataMoviesResponse::getDirector, Collectors.counting()))
                .map(directorCount -> directorCount.entrySet().stream()
                        .filter(entry -> entry.getValue() >= threshold)
                        .map(Map.Entry::getKey)
                        .sorted()
                        .collect(Collectors.toList())
                )
                .map(directors -> Map.of("directors", directors));
    }
}
