package com.masivian.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;



import org.springframework.data.annotation.Reference;

import org.springframework.data.redis.core.index.Indexed;

import com.masivian.utilities.Utilities;


public class Roulette implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Indexed
	private String status;
	
	@Reference
	private List <Bet> betsOfRoulette = new ArrayList<Bet>();
	
	

	public Roulette() {

		this.id = Utilities.generateId();
		this.status = "Closed";
		
		
		betsOfRoulette.add(new Bet(2));
		
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
