package com.netflix.entity;

import com.netflix.dto.StreamingDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name="catalogo_streaming")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class StreamingEntity {
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="show_id") private long showId;
    @Column(name="typee") private String typee;

    @Column(name="title") private String title;
    @Column(name="director", columnDefinition="TEXT") private String director;
    @Column(name="castt", columnDefinition="TEXT") private String castt;

    @Column(name="country") private String country;
    @Column(name="date_added") private String dateAdded;
    @Column(name="release_year") private String releaseYear;

    @Column(name="rating") private String rating;
    @Column(name="duration") private String duration;
    @Column(name="listed_in") private String listedIn;

    @Column(name="description", columnDefinition="TEXT") private String description;

    public void fromEntity(StreamingDTO movieDTO) {
        this.setTypee(movieDTO.typee());
        this.setTitle(movieDTO.title());
        this.setDirector(movieDTO.director());
        this.setCastt(movieDTO.castt());
        this.setCountry(movieDTO.country());
        this.setDateAdded(movieDTO.dateAdded());
        this.setReleaseYear(movieDTO.releaseYear());
        this.setRating(movieDTO.rating());
        this.setDuration(movieDTO.duration());
        this.setListedIn(movieDTO.listedIn());
        this.setDescription(movieDTO.description());
    }
}
