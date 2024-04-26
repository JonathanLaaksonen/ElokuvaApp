package com.harjoitusteht.elokuvaapp;

import com.harjoitusteht.elokuvaapp.controller.MovieController;
import com.harjoitusteht.elokuvaapp.model.Movie;
import com.harjoitusteht.elokuvaapp.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    @Test
    @WithMockUser(username="user", roles="USER")
    void listMoviesShouldReturnOkStatus() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin", roles="ADMIN")
    void addMovieShouldReturnRedirectStatus() throws Exception {
        Movie movie = new Movie("Test Movie", 2021, "Test Genre", "Test Description");
        when(movieService.saveMovie(any(Movie.class))).thenReturn(movie);

        mockMvc.perform(post("/movies") 
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("title", movie.getTitle())
                .param("releaseYear", movie.getReleaseYear().toString())
                .param("genre", movie.getGenre())
                .param("description", movie.getDescription())
                .with(SecurityMockMvcRequestPostProcessors.csrf())) 
                .andExpect(status().is3xxRedirection());
    }

}