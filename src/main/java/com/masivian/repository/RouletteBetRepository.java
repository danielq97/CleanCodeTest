package com.masivian.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.masivian.model.RouletteBet;

public interface RouletteBetRepository extends MongoRepository<RouletteBet, Long> {

}
