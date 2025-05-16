package domus.challenge.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiDataMoviesResponse {
    @JsonProperty("Title")
    private String title;
    @JsonProperty("year")
    private String year;
    @JsonProperty("Director")
    private String director;
    @JsonProperty("Genre")
    private String genre;
    @JsonProperty("Actors")
    private String actors;
    @JsonProperty("Writer")
    private String writer;
    @JsonProperty("Rated")
    private String rated;
    @JsonProperty("Released")
    private String released;
    @JsonProperty("Runtime")
    private String runtime;

}

