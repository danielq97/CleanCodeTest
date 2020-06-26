package com.masivian.utilities;

import java.io.Serializable;
import java.util.Random;

import com.masivian.model.Roulette;

public class Utilities implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long generateId() {
		long tmp = new Random().nextLong();

		return Math.max(tmp, tmp * -1);

	}
	
	public static boolean rouletteIsOpen(Roulette roulette) {
		return  roulette.getStatus().equals("Open") ;
	}

	
	public static boolean IsANumber(String input) {
		
		try {
			int number = Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		
		
		
	}
}
	
	
