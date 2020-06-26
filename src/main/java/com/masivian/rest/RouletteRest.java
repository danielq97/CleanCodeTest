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
import com.masivian.model.Roulette;

@RestController
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
	@PostMapping("/createNewRoulette")
	public String createRoulette() {
		Roulette newRoulette = new Roulette();
		rouletteRepo.save(newRoulette);

		return "La nueva ruleta fue creada con el id " + newRoulette.getId();
	}

	/**
	 * Servive that allow open a roulette for allow bets
	 * 
	 * @param id - id of Roulette
	 * @return
	 */
	@PutMapping("/openRoulette/{id}")
	public String openRoulette(@PathVariable("id") final long id) {
		String response = "Operación denegada";
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

	@PutMapping("/wager/{idRoulette}/{bet}")
	public String wager(@PathVariable("idRoulette") final long idRoulette, @PathVariable("bet") final String bet) {

		Roulette roulette = rouletteRepo.findById(idRoulette).orElse(null);

		if (roulette != null) {
			if (Utilities.rouletteIsOpen(roulette)) {
				RouletteBet newBet = new RouletteBet();
				if (Utilities.IsANumber(bet)) 
					newBet.setNumber(bet);
				else
					newBet.setColor(bet);
				roulette.addBet(newBet);
				rouletteRepo.save(roulette);
				betRepo.save(newBet);

			}
		}

		return "Fue hecha la apuesta";
	}
	
	
	@PutMapping("/closeRoulette/{idRoulette}")
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
	
	@GetMapping("/roulettes")
	public String findAllRoulettes() {

		String response = "";
		
		
		
		ArrayList<Roulette> roulettes = (ArrayList<Roulette>) rouletteRepo.findAll();
		for(Roulette roulette : roulettes) {
			response += "RouletteId:" + "\t" + roulette.getId()+ "\n" + "Status:" + "\t\t" + roulette.getStatus() + "\n\n";
		}
		
		return response;
	}

//	
//	
//
//	@GetMapping("/roulette/{rouletteId}")
//	@ResponseBody
//	public ResponseEntity<Roulette> getRoulette(@PathVariable long rouletteId) {
//		Roulette roulette = rouletteRepo.getRoulette(rouletteId);
//
//		return new ResponseEntity<Roulette>(roulette, HttpStatus.OK);
//	}
//
//	@PostMapping(value = "/addRoulette", consumes = { "application/json" }, produces = { "application/json" })
//	@ResponseBody
//	public String addRoulette(@RequestBody Roulette roulette, UriComponentsBuilder builder) {
//		rouletteRepo.addRoulette(roulette);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(builder.path("/addRoulette/{id}").buildAndExpand(roulette.getId()).toUri());
//		return "El id de la ruleta creada es " + roulette.getId();
//	}

//	    @RequestMapping("/getAllRoulettes")
//	    @ResponseBody
//	    public ResponseEntity<Map<Long,Roulette>> getAllRoulettes(){
//	        Map<Long,Roulette> roulettes =  rouletteRepo.getAllRoulettes();
//	        return new ResponseEntity<Map<Long,Roulette>>(roulettes, HttpStatus.OK);
//	    }
//	    
	@GetMapping("/getAllRoulettes")
	public List<Roulette> findAll() {

		return rouletteRepo.findAll();
	}
}


