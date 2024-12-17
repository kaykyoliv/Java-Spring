package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(nativeQuery = true, value = """
            SELECT tb_review.*
            FROM tb_review
            INNER JOIN tb_movie ON tb_movie.id = tb_review.movie_id
            WHERE tb_movie.id = :movieId      
            """)
    List<Review> searchReviewById(Long movieId);
}
