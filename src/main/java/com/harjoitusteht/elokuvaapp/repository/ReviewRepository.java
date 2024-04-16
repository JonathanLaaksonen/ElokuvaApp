package com.harjoitusteht.elokuvaapp.repository;

import com.harjoitusteht.elokuvaapp.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Voit lisätä metodeja, jotka hakevat arvostelut tietylle elokuvalle tai käyttäjälle, esim.:
    // List<Review> findByMovieId(Long movieId);
}