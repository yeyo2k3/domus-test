package domus.challenge.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApiMoviesResponse {
    @JsonProperty("data")
    private List<ApiDataMoviesResponse> movies;

    @JsonProperty("total_pages")
    private int totalPages;
}
