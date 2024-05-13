package com.pokedex.pokemon.util;

import org.springframework.stereotype.Component;

import com.pokedex.pokemon.dto.PokemonDTO;
import com.pokedex.pokemon.entity.Pokemon;

@Component
public class PokemonMapper {
	
	public Pokemon covertToEntity(PokemonDTO dto) {
		Pokemon entity = new Pokemon();
		entity.setImg(dto.getImg());
		entity.setName(dto.getName());
		entity.setNext_evolution(dto.getNext_evolution());
		entity.setNumber(dto.getNumber());
		entity.setType(dto.getType());
		entity.setWeaknesses(dto.getWeaknesses());
		entity.setWeight(dto.getWeight());
		
		return entity;
	}

}
