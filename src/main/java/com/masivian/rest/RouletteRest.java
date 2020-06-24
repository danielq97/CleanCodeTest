package com.masivian.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouletteRest {

	
	
	@GetMapping("daniel")
	public String helloWorld() {
		return "hola";
	}
	
	
	
}
