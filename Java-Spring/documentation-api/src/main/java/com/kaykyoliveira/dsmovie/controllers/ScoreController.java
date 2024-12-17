package com.kaykyoliveira.dsmovie.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaykyoliveira.dsmovie.dto.MovieDTO;
import com.kaykyoliveira.dsmovie.dto.ScoreDTO;
import com.kaykyoliveira.dsmovie.services.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/scores")
public class ScoreController {
	
	@Autowired
	private ScoreService service;
	
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
	@PutMapping(produces = "application/json")
	public MovieDTO saveScore(@Valid @RequestBody ScoreDTO dto) {
		MovieDTO movieDTO = service.saveScore(dto);
		return movieDTO;
	}
}