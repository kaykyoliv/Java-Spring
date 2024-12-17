package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.services.GerneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/genres")
public class GenreController {

    @Autowired
    private GerneService service;

    @PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_VISITOR')")
    @GetMapping
    public ResponseEntity<List<GenreDTO>> findAll(){
        List<GenreDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }
}
