package com.harjoitusteht.elokuvaapp.repository;

import com.harjoitusteht.elokuvaapp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Voit lisätä mukautettuja kyselymetodeja tähän, esimerkiksi:
    // List<Movie> findByGenre(String genre);
}