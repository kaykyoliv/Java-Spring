package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {


    @Query(nativeQuery = true, value = """
            SELECT * FROM (
                SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title AS subTitle, tb_movie.movie_year as movieYear, tb_movie.img_url AS imgUrl
                FROM tb_movie
                INNER JOIN tb_genre ON tb_genre.id = tb_movie.genre_id
                WHERE (:genreId IS NULL OR tb_movie.genre_id IN :genreId)
                ORDER BY tb_movie.title
            ) AS tb_result      
            """, countQuery = """
            SELECT COUNT(*) FROM (
                SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title AS subTitle, tb_movie.movie_year as movieYear, tb_movie.img_url AS imgUrl
                FROM tb_movie
                INNER JOIN tb_genre ON tb_genre.id = tb_movie.genre_id
                WHERE (:genreId IS NULL OR tb_movie.genre_id IN :genreId)
                ORDER BY tb_movie.title
            ) AS tb_result  
            """)
    Page<MovieCardProjection> searchByGenre(List<Long> genreId, Pageable pageable);

}
