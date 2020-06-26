package com.masivian.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("bets")
public class Bet implements Serializable{

	
	
	
	
	@Indexed
	private Long rouletteId;
	
	@Id
	private Long id;
	
	
	
	private String color;
	
	private int number;
	
	private String result;
	
	
	public Bet(int number) {
		this.rouletteId = 2L;
		this.id = 2L;
		this.color = "Rojo";
		this.number = number;
		this.result = "not";
	}
	
	
}
