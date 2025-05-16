package domus.challenge.controller;

import domus.challenge.service.DirectorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class DirectorController {

    @Autowired
    private DirectorService directorService;


    @Operation(
            summary = "Get a directors list who have directed more than specific threshold",
            description = "Return an ordered director list who have directed more movies than threshold given.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Director List"),
                    @ApiResponse(responseCode = "400", description = "threshold is not valid")
            }
    )
    @GetMapping("directors")
    public Mono<ResponseEntity<?>> getDirectors(@RequestParam Long threshold) {
        try{
            Mono<Map<String, List<String>>> directors = directorService.getDirectors(threshold);
            return directors.map(ResponseEntity::ok);
        }catch (NumberFormatException nfe){
            return Mono.just(ResponseEntity.badRequest().body(Map.of("error", "Threshold must be a valid number")));
        }

    }
}
