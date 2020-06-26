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
	private ArrayList <RouletteBet> betsOfRoulette = new ArrayList<RouletteBet>();
	
	

	private RouletteResult result;
	
	public RouletteResult getResult() {
		return result;
	}



	public void setResult(RouletteResult result) {
		this.result = result;
	}



	public Roulette() {

		this.id = Utilities.generateId();
		this.status = "Closed";	
		
		
	}



	public ArrayList<RouletteBet> getBetsOfRoulette() {
		return betsOfRoulette;
	}



	public void setBetsOfRoulette(ArrayList<RouletteBet> betsOfRoulette) {
		this.betsOfRoulette = betsOfRoulette;
	}
	public void addBet(RouletteBet bet) {
		this.betsOfRoulette.add(bet);
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
