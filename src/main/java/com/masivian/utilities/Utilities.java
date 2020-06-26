package com.masivian.utilities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


import com.masivian.model.Roulette;
import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;

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
		return roulette.getStatus().equals("Open");
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
	
	public static String colorForResult(int number) {
		if(number==0) {
			return "Green";
		}else {
			List<Integer> blackNumbers = Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,36);
			if(blackNumbers.contains(number)) {
				return "Black";
			}else
				return "Red";
		
			
		}
		
	}
	
	public static String resultOfBet(RouletteBet rouletteBet,RouletteResult rouletteResult) {
		if(rouletteBet.getColor()!=null) {
			if(rouletteBet.getColor().equals(rouletteResult.getColor())) {
				return "Won";
			}else
				return "Lost";
		}else {
			if(rouletteBet.getNumber().equals(rouletteResult.getNumber())) {
				return "Won";
			}else
				return "Lost";
		}
	}
	
}
