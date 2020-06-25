package com.masivian.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.masivian.model.Roulette;
import com.masivian.repository.RouletteRepository;
import com.masivian.utilities.Utilities;

@RestController
public class RouletteRest {

	@Autowired
	RouletteRepository rouletteRepo;

	/**
	 * Service that allow the creation of new roulettes
	 * 
	 * @return String - A message indicating the id of the new roulette created
	 */
	@PostMapping("/createNewRoulette")
	public String createRoulette() {
		Roulette newRoulette = new Roulette();
		rouletteRepo.addRoulette(newRoulette);
		return "La nueva ruleta fue creada con el id " + newRoulette.getId();
	}

	@GetMapping("/openRoulette/{id}")
	public String openRoulette(@PathVariable("id") final long id) {
		String response = "Operación denegada";
		Roulette roulette = rouletteRepo.getRoulette(id);
		if (roulette != null) {
			if (!Utilities.rouletteIsOpen(roulette)) {
				roulette.setStatus("Open");
				rouletteRepo.updateRoulette(roulette);
				response = "Operación éxitosa";
			} else
				response = "Operación denegada";
		}

		return response;
	}
	
	

	@GetMapping("/roulette/{rouletteId}")
	@ResponseBody
	public ResponseEntity<Roulette> getRoulette(@PathVariable long rouletteId) {
		Roulette roulette = rouletteRepo.getRoulette(rouletteId);

		return new ResponseEntity<Roulette>(roulette, HttpStatus.OK);
	}

	@PostMapping(value = "/addRoulette", consumes = { "application/json" }, produces = { "application/json" })
	@ResponseBody
	public String addRoulette(@RequestBody Roulette roulette, UriComponentsBuilder builder) {
		rouletteRepo.addRoulette(roulette);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/addRoulette/{id}").buildAndExpand(roulette.getId()).toUri());
		return "El id de la ruleta creada es " + roulette.getId();
	}

//	    @RequestMapping("/getAllRoulettes")
//	    @ResponseBody
//	    public ResponseEntity<Map<Long,Roulette>> getAllRoulettes(){
//	        Map<Long,Roulette> roulettes =  rouletteRepo.getAllRoulettes();
//	        return new ResponseEntity<Map<Long,Roulette>>(roulettes, HttpStatus.OK);
//	    }
//	    
	@GetMapping("/getAllRoulettes")
	public Map<Long, Roulette> findAll() {
		return rouletteRepo.getAllRoulettes();
	}

}
