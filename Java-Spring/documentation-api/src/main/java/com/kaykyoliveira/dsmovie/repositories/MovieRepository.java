package com.kaykyoliveira.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaykyoliveira.dsmovie.entities.MovieEntity;

public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

}