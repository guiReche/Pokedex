package com.pokedex.pokemon.dto;

import java.util.List;

import org.modelmapper.ModelMapper;

import com.pokedex.pokemon.entity.NextEvolution;
import com.pokedex.pokemon.entity.Pokemon;

public class PokemonDTO {
	
	private Long id;
	
	private String number;
	
	private String name;
	
	private String weight;
	
	private List<String> type;
	
	private List<String> weaknesses;
	
	private String img;
	
	private List<NextEvolution> next_evolution;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String num) {
		this.number = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getWeaknesses() {
		return weaknesses;
	}

	public void setWeaknesses(List<String> weaknesses) {
		this.weaknesses = weaknesses;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<NextEvolution> getNext_evolution() {
		return next_evolution;
	}

	public void setNext_evolution(List<NextEvolution> next_evolution) {
		this.next_evolution = next_evolution;
	}
	
    static ModelMapper getModelMapper() {
        return new ModelMapper();
    }
    
    public static PokemonDTO convertToDTO(Pokemon entity) {
        return getModelMapper().map(entity, PokemonDTO.class);
    }
}
