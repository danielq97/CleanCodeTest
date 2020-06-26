package com.masivian.model;

import java.io.Serializable;

import com.masivian.utilities.Utilities;

public class RouletteResult implements Serializable {

	private String color;
	private String number;
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public RouletteResult() {
		//Random number between 0 and 36
		this.number = ((int)(Math.random()*(36))) + "";
		this.color = Utilities.colorForResult(Integer.parseInt(number));
	}
}
