package com.masivian.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.masivian.model.Bet;

public interface BetRepository extends MongoRepository<Bet, Long>{

}
