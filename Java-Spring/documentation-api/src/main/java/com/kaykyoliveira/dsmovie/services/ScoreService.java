package com.kaykyoliveira.dsmovie.services;

import com.kaykyoliveira.dsmovie.entities.ScoreEntity;
import com.kaykyoliveira.dsmovie.entities.UserEntity;
import com.kaykyoliveira.dsmovie.repositories.MovieRepository;
import com.kaykyoliveira.dsmovie.repositories.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kaykyoliveira.dsmovie.dto.MovieDTO;
import com.kaykyoliveira.dsmovie.dto.ScoreDTO;
import com.kaykyoliveira.dsmovie.entities.MovieEntity;

@Service
public class ScoreService {

	@Autowired
	private UserService userService;
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ScoreRepository scoreRepository;
	
	@Transactional
	public MovieDTO saveScore(ScoreDTO dto) {
		
		UserEntity user = userService.authenticated();
		
		MovieEntity movie = movieRepository.findById(dto.getMovieId()).get();
		
		ScoreEntity score = new ScoreEntity();
		score.setMovie(movie);
		score.setUser(user);
		score.setValue(dto.getScore());
		
		score = scoreRepository.saveAndFlush(score);
		
		double sum = 0.0;
		for (ScoreEntity s : movie.getScores()) {
			sum = sum + s.getValue();
		}
			
		double avg = sum / movie.getScores().size();
		
		movie.setScore(avg);
		movie.setCount(movie.getScores().size());
		
		movie = movieRepository.save(movie);
		
		return new MovieDTO(movie);
	}
}
