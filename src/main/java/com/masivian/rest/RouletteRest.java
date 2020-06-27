package com.masivian.rest;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;
import com.masivian.repository.RouletteBetRepository;
import com.masivian.repository.RouletteRepository;
import com.masivian.utilities.Utilities;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.masivian.model.Roulette;

@RestController
@RequestMapping("/roulettes")
@Tag(name = "roulette", description = "The roulette API")
public class RouletteRest {
	@Autowired
	RouletteRepository rouletteRepo;
	@Autowired
	RouletteBetRepository betRepo;

	@PostMapping
	public String createRoulette() {
		Roulette newRoulette = new Roulette();
		rouletteRepo.save(newRoulette);

		return "A new roulette was created with id " + newRoulette.getId();
	}

	@PutMapping(value = "/{rouletteId}/openRoulette")
	public String openRoulette(@PathVariable("rouletteId") final long rouletteId) {
		String response = "Operation rejected";
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (!Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Open");
				rouletteRepo.save(roulette);
				response = "Succesful operation";
			}
		}

		return response;
	}

	@PutMapping(value = "/{rouletteId}/{bet}/{value}")
	public String wager(@RequestHeader(value = "userId") long userId, @PathVariable("rouletteId") final long rouletteId,
			@PathVariable("bet") final String bet, @PathVariable("value") final int value) {
		String response = "";
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette) && Utilities.betIsValid(bet, value)) {
				RouletteBet newBet = new RouletteBet();
				if (Utilities.IsANumber(bet))
					newBet.setNumber(bet);
				else
					newBet.setColor(bet);
				newBet.setValue(value);
				roulette.addBet(newBet);
				rouletteRepo.save(roulette);
				betRepo.save(newBet);
				response = "The bet was made";
			} else
				response = "The bet wasn't made because roulette is closed or bet is not valid";
		} else
			response = "The bet wasn't made because id of roulette doesn't exist";

		return response;
	}

	@PutMapping(value = "/{rouletteId}/closeRoulette")
	public Roulette closeRoulette(@PathVariable("rouletteId") final long rouletteId) {
		Roulette roulette = rouletteRepo.findById(rouletteId).orElse(null);
		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Closed");
				RouletteResult rouletteResult = new RouletteResult();
				roulette.setResult(rouletteResult);
				ArrayList<RouletteBet> rouletteBets = roulette.getBetsOfRoulette();
				for (RouletteBet rouletteBet : rouletteBets) {
					rouletteBet.setResult(Utilities.resultOfBet(rouletteBet, rouletteResult));
				}
				roulette.setBetsOfRoulette(rouletteBets);
				rouletteRepo.save(roulette);
				betRepo.saveAll(rouletteBets);
			}
		}

		return roulette;
	}

	@GetMapping
	public String getAllRoulettesWithStatus() {
		String response = "";
		ArrayList<Roulette> roulettes = (ArrayList<Roulette>) rouletteRepo.findAll();
		for (Roulette roulette : roulettes) {
			response += "RouletteId:" + "\t" + roulette.getId() + "\n" + "Status:" + "\t\t" + roulette.getStatus()
					+ "\n\n";
		}

		return response;
	}
}
