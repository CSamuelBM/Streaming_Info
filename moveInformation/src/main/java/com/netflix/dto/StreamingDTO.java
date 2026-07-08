package com.netflix.dto;

import com.netflix.entity.StreamingEntity;

public record StreamingDTO(
        String typee, String title, String director,
        String castt, String country, String dateAdded,
        String releaseYear, String rating, String duration,
        String listedIn, String description
) {
    public static StreamingDTO fromDTO(StreamingEntity movie) {
        if(movie == null)
            return null;
        return new StreamingDTO(
                movie.getTypee(), movie.getTitle(), movie.getDirector(),
                movie.getCastt(), movie.getCountry(), movie.getDateAdded(),
                movie.getReleaseYear(), movie.getRating(), movie.getDuration(),
                movie.getListedIn(), movie.getDescription()
        );
    }
}
