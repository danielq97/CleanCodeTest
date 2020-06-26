package com.masivian.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.masivian.utilities.Utilities;


public class Bet implements Serializable{

	
	
	
	

	@Id
	private Long id;
	
	
	
	private String color;	
	private int number;	
	private String result;
	
	
	public Bet(int number) {
		
		this.id = Utilities.generateId();
		this.color = "Rojo";
		this.number = number;
		this.result = "not";
	}
	
	
}
