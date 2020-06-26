package com.masivian.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.masivian.utilities.Utilities;

@Document(collection = "Roulettes")
public class Roulette implements Serializable{



	@Id
	private long id;

	
	private String status;
	
	@DBRef
	private List <Bet> betsOfRoulette = new ArrayList<Bet>();
	
	

	public Roulette() {

		this.id = Utilities.generateId();
		this.status = "Closed";	
		
		
	}

	public List<Bet> getBetsOfRoulette() {
		return betsOfRoulette;
	}

	public void setBetsOfRoulette(List<Bet> betsOfRoulette) {
		this.betsOfRoulette = betsOfRoulette;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	


}
