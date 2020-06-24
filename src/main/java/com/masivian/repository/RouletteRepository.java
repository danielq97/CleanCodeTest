package com.masivian.repository;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.masivian.model.Roulette;

@Repository
public class RouletteRepository {

	
	public static final String KEY = "ROULETTE";
	private RedisTemplate<String, Roulette> redisTemplate;
	private HashOperations hashOperations;
	
	
	 public RouletteRepository(RedisTemplate<String, Roulette> redisTemplate) {
	        this.redisTemplate = redisTemplate;
	        hashOperations = redisTemplate.opsForHash();
	    }

	    
	    public Map<Integer,Roulette> getAllRoulettes(){
	        return hashOperations.entries(KEY);
	    }

	   
	    public Roulette getRoulette(int rouletteId){
	        return (Roulette) hashOperations.get(KEY,rouletteId);
	    }

	    
	    public void addRoulette(Roulette roulette){
	        hashOperations.put(KEY,roulette.getId(),roulette);
	    }

	    
	    public void deleteRoulette(int id){
	        hashOperations.delete(KEY,id);
	    }

	   
	    public void updateRoulette(Roulette roulette){
	        addRoulette(roulette);
	    }
	
	
	
}
