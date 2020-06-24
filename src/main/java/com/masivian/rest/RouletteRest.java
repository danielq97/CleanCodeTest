package com.masivian.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.masivian.model.Roulette;
import com.masivian.repository.RouletteRepository;

@RestController
public class RouletteRest {

	
	
	@Autowired
	RouletteRepository rouletteRepo;
	
	
	
	    @GetMapping("/roulette/{rouletteId}")
	    @ResponseBody
	    public ResponseEntity<Roulette> getRoulette(@PathVariable int rouletteId){
	        Roulette roulette = rouletteRepo.getRoulette(rouletteId);
	        
	        return new ResponseEntity<Roulette>(roulette, HttpStatus.OK);
	    }

	    @PostMapping(value = "/addRoulette",consumes = {"application/json"},produces = {"application/json"})
	    @ResponseBody
	    public String addRoulette(@RequestBody Roulette roulette,UriComponentsBuilder builder){
	        rouletteRepo.addRoulette(roulette);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(builder.path("/addRoulette/{id}").buildAndExpand(roulette.getId()).toUri());
	        return "El id de la ruleta creada es " + roulette.getId();
	    }
	
	
}
