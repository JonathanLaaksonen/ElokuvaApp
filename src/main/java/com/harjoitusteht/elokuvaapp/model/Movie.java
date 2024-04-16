package com.harjoitusteht.elokuvaapp.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private Integer releaseYear;
    private String genre;
    private String description;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
    // Oletuskonstruktori
    public Movie() {
    }

    // Parametrillinen konstruktori
    public Movie(String title, Integer releaseYear, String genre, String description) {
        this.title = title;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.description = description;
    }

    // Getterit
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    // Setterit
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // toString-metodi (valinnainen, mutta hyödyllinen esim. loggauksessa)
    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseYear=" + releaseYear +
                ", genre='" + genre + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}