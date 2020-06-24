package com.masivian;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.masivian.model.Roulette;


@SpringBootApplication
public class RouletteWsApplication {


	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	RedisTemplate<String, Roulette> redisTemplate(){
		RedisTemplate<String, Roulette> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}
	

	
	
	public static void main(String[] args) {
		SpringApplication.run(RouletteWsApplication.class, args);
	}

}
