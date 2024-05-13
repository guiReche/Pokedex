package com.pokedex.pokemon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pokedex.pokemon.entity.NextEvolution;
import com.pokedex.pokemon.repository.NextEvolutionRepository;

@Service
public class EvolutionService {

	@Autowired 
	NextEvolutionRepository nextEvolutionRepository;
	
	public NextEvolution getById(Long id) {
		return getEvolutionRepository().findById(id).orElseThrow(null);
	}
	
	public void delete(Long id) {
		getEvolutionRepository().deleteById(id);
	}
	
	public NextEvolutionRepository getEvolutionRepository() {
		return nextEvolutionRepository;
	}
}
