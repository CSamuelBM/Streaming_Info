package com.netflix.controller;

import com.netflix.dto.StreamingDTO;
import com.netflix.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moveInformation")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StreamingController {

    private final StreamingService streamingService;

    @GetMapping("/{showId}")
    public StreamingDTO getMovieId(@PathVariable long showId){
        return streamingService.getStreamingId(showId);
    }

    @GetMapping("/all")
    public ResponseEntity<PagedModel<StreamingDTO>> getMovieAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "25") int size
    ){
        Page<StreamingDTO> pageMovie = streamingService.getStreamingAll(page, size);
        return ResponseEntity.ok(new PagedModel<>(pageMovie));
    }

    @GetMapping("/search")
    public ResponseEntity<List<StreamingDTO>> getMovie(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String duration,
            @RequestParam(required = false) String typee,
            @RequestParam(required = false) String releaseYear
    ) {
        return ResponseEntity.ok(streamingService.getStreamingSearch(title, duration, typee, releaseYear));
    }

    @PostMapping()
    public void postMovie(@RequestBody StreamingDTO movie) {
        streamingService.setMovie(movie);
    }

    @PatchMapping("/{showId}")
    public void updateMovie(@PathVariable long showId, @RequestBody StreamingDTO movieDTO){
        streamingService.updateMovie(showId, movieDTO);
    }

    @DeleteMapping("/{showId}")
    public void deleteMovie(@PathVariable long showId) {
        streamingService.deleteMovie(showId);
    }
}
