package com.harjoitusteht.elokuvaapp.model;

import jakarta.persistence.*;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String text;
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

   
    public Review() {
    }

   
    public Review(String text, Integer rating, Movie movie, User user) {
        this.text = text;
        this.rating = rating;
        this.movie = movie;
        this.user = user;
    }

  
    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getRating() {
        return rating;
    }

    public Movie getMovie() {
        return movie;
    }

    public User getUser() {
        return user;
    }

   
    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setUser(User user) {
        this.user = user;
    }

   
    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", rating=" + rating +
                ", movie=" + (movie != null ? movie.getTitle() : "none") +
                ", user=" + (user != null ? user.getUsername() : "none") +
                '}';
    }
}