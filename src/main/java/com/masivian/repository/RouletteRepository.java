package com.masivian.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.masivian.model.Roulette;

public interface RouletteRepository extends MongoRepository<Roulette, Long> {

}
