package com.masivian.model;

import java.io.Serializable;

public class Roulette implements Serializable {

	private int id;
	private String status;

	public Roulette() {

	}

	public Roulette(int id, String status) {
		super();
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
