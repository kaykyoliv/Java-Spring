package com.devsuperior.movieflix.services;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.projections.MovieCardProjection;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public MovieDetailsDTO findById(Long id){
       Optional<Movie> obj = repository.findById(id);
       Movie entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
       return new MovieDetailsDTO(entity);
    }

    @Transactional
    public List<ReviewDTO> findAllReview(Long id){
        List<Review> list = reviewRepository.searchReviewById(id);
        return list.stream().map(ReviewDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public Page<MovieCardDTO> searchByGenre(String genreId, Pageable pageable){

        List<Long> genreIds = Arrays.asList();
        if(!"0".equals(genreId)){
            genreIds = Arrays.asList(genreId.split(",")).stream().map(Long::parseLong).toList();
        }

        Page<MovieCardProjection> projections = repository.searchByGenre(genreIds, pageable);
        List<MovieCardDTO> list = projections.stream().map(MovieCardDTO::new).collect(Collectors.toList());

        return new PageImpl<>(list, projections.getPageable(), projections.getTotalElements());
    }

}
