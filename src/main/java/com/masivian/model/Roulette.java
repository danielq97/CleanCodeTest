package com.masivian.model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;


import org.springframework.data.annotation.Id;

import com.masivian.utilities.Utilities;

public class Roulette implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String status;
	
	
	private Bet bet;
	
	

	public Roulette() {

		this.id = Utilities.generateId();
		this.status = "Closed";
		
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
	public Bet getBet() {
		return bet;
	}

	public void setBet(Bet bet) {
		this.bet = bet;
	}


}
