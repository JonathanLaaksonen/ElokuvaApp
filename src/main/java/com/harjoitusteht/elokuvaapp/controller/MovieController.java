package com.harjoitusteht.elokuvaapp.controller;

import com.harjoitusteht.elokuvaapp.model.Movie;
import com.harjoitusteht.elokuvaapp.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Listaa kaikki elokuvat
    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.findAllMovies());
        return "movies"; // Thymeleaf-template tiedostonimi movies.html
    }

    // Näyttää lomakkeen uuden elokuvan lisäämiseksi
    @GetMapping("/movies/add")
    public String showAddMovieForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "add-movie"; // Thymeleaf-template tiedostonimi add-movie.html
    }

    // Käsittelee uuden elokuvan lisäämisen
    @PostMapping("/movies")
    public String addMovie(@ModelAttribute Movie movie) {
        movieService.saveMovie(movie);
        return "redirect:/movies"; // Ohjaa takaisin elokuvien listaukseen
    }

    // Näyttää lomakkeen elokuvan tietojen päivittämiseksi
    @GetMapping("/movies/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Movie movie = movieService.findMovieById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
        model.addAttribute("movie", movie);
        return "update-movie"; // Thymeleaf-template tiedostonimi update-movie.html
    }

    // Käsittelee elokuvan tietojen päivittämisen
    @PostMapping("/movies/update/{id}")
    public String updateMovie(@PathVariable("id") Long id, @ModelAttribute Movie movie) {
        movie.setId(id);
        movieService.saveMovie(movie);
        return "redirect:/movies"; // Ohjaa takaisin elokuvien listaukseen
    }

    // Käsittelee elokuvan poistamisen
    @GetMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id) {
        movieService.deleteMovie(id);
        return "redirect:/movies"; // Ohjaa takaisin elokuvien listaukseen
    }
}