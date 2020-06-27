package com.masivian.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import org.springframework.web.bind.annotation.*;

import com.masivian.model.RouletteBet;
import com.masivian.model.RouletteResult;
import com.masivian.repository.RouletteBetRepository;
import com.masivian.repository.RouletteRepository;
import com.masivian.utilities.Utilities;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

	/**
	 * Service that allow the creation of new roulettes
	 * 
	 * @return String - A message indicating the id of the new roulette created
	 */
	@PostMapping
	public String createRoulette() {
		Roulette newRoulette = new Roulette();
		rouletteRepo.save(newRoulette);

		return "La nueva ruleta fue creada con el id " + newRoulette.getId();
	}

	/**
	 * Service that allow open a roulette for bets.
	 * 
	 * @param id - id of Roulette
	 * @return
	 */
	@Operation(summary = "Create new roulette", description = "Service that allow the creation of new roulettes", tags = "roulette")
	@Parameters(@Parameter(name = "id", required = true, description = "id of roulette"))
	@PutMapping(value = "/{id}/openRoulette")
	public String openRoulette(@PathVariable("id") final long id) {
		String response = "Operation rejected";
		Roulette roulette = rouletteRepo.findById(id).orElse(null);
		if (roulette != null) {
			if (!Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Open");
				rouletteRepo.save(roulette);
				response = "Operación éxitosa";
			}
		}

		return response;
	}

	@PutMapping(value = "/{idRoulette}/{bet}/{value}")
	public String wager(@PathVariable("idRoulette") final long idRoulette, @PathVariable("bet") final String bet,
			@PathVariable("value") final String value) {
		Roulette roulette = rouletteRepo.findById(idRoulette).orElse(null);
		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette)) {
				RouletteBet newBet = new RouletteBet();
				if (Utilities.IsANumber(bet))
					newBet.setNumber(bet);
				else
					newBet.setColor(bet);
				newBet.setValue(value);
				roulette.addBet(newBet);
				rouletteRepo.save(roulette);
				betRepo.save(newBet);
			}
		}

		return "Fue hecha la apuesta";
	}

	@PutMapping(value = "/{idRoulette}/closeRoulette")
	public Roulette closeRoulette(@PathVariable("idRoulette") final long idRoulette) {
		Roulette roulette = rouletteRepo.findById(idRoulette).orElse(null);
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
	public String getAllRoulettes() {
		String response = "";
		ArrayList<Roulette> roulettes = (ArrayList<Roulette>) rouletteRepo.findAll();
		for (Roulette roulette : roulettes) {
			response += "RouletteId:" + "\t" + roulette.getId() + "\n" + "Status:" + "\t\t" + roulette.getStatus()
					+ "\n\n";
		}

		return response;
	}
}
