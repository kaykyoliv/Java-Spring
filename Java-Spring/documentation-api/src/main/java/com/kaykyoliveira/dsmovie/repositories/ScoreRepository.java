package com.kaykyoliveira.dsmovie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaykyoliveira.dsmovie.entities.ScoreEntity;
import com.kaykyoliveira.dsmovie.entities.ScoreEntityPK;

public interface ScoreRepository extends JpaRepository<ScoreEntity, ScoreEntityPK> {

}