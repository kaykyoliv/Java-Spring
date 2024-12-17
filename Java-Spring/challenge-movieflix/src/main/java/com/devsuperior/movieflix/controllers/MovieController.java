package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.MovieCardDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    private MovieService service;

    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<MovieDetailsDTO> findById(@PathVariable Long id){
        MovieDetailsDTO dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }
    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    @GetMapping
    public ResponseEntity<Page<MovieCardDTO>> searchByGenre(
            @RequestParam(name = "genreId", defaultValue = "0") String genreId,
            Pageable pageable){
        Page<MovieCardDTO> page = service.searchByGenre(genreId, pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping(value = "/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> allReview(@PathVariable Long id){
        List<ReviewDTO> list = service.findAllReview(id);
        return ResponseEntity.ok(list);
    }
}
