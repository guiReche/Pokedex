package com.pokedex.pokemon.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.pokedex.pokemon.dto.PokemonDTO;
import com.pokedex.pokemon.dto.PokemonFilterDTO;
import com.pokedex.pokemon.entity.NextEvolution;
import com.pokedex.pokemon.entity.Pokemon;
import com.pokedex.pokemon.exception.PokemonDTOMissingException;
import com.pokedex.pokemon.exception.PokemonNameMissingException;
import com.pokedex.pokemon.repository.NextEvolutionRepository;
import com.pokedex.pokemon.repository.PokemonRepository;
import com.pokedex.pokemon.util.PokemonMapper;
import com.pokedex.pokemon.util.PokemonSpecifications;

@Service
public class PokemonService {

	@Autowired
	private PokemonRepository pokemonRepository;

	@Autowired
	private NextEvolutionRepository nextEvolutionRepository;
	
	@Autowired
	private PokemonMapper pokemonMapper;

	public List<PokemonDTO> getAll() {
		List<Pokemon> listPokemonEntity = getPokemonRepository().findAll();
        List<PokemonDTO> listPokemonDTO = listPokemonEntity.stream().map(f -> PokemonDTO.convertToDTO(f)).toList();

		return listPokemonDTO;
	}

	public Pokemon getById(Long id) {
		return getPokemonRepository().findById(id).orElse(null);
	}
	
	//Using cutomized findAll with specification class
	public List<Pokemon> findPokemonsByAttributes(PokemonFilterDTO pokemon) {
		Specification<Pokemon> specification = PokemonSpecifications.withAttributes(pokemon);
		return getPokemonRepository().findAll(specification);
	}

	public void save(PokemonDTO pokemonDTO) {
		if(pokemonDTO != null) {
			if(pokemonDTO.getName() == null) {
				throw new PokemonNameMissingException();
			} else {
				Pokemon pokemonEntity = pokemonMapper.covertToEntity(pokemonDTO);
				getPokemonRepository().save(pokemonEntity);
				if (!pokemonDTO.getNext_evolution().isEmpty()) {
					for (NextEvolution evolution : pokemonDTO.getNext_evolution()) {
						evolution.setPokemon(pokemonEntity);
						getEvolutionRepository().save(evolution);
					}
				}
			}
		} else {
			throw new PokemonDTOMissingException();
		}
	}

	public String update(Pokemon pokemon, Long id) {
		Pokemon originalPokemon = getById(id);
		String responseMessage = "Pokemon of ID " + id + " not found";
		

		if (originalPokemon != null) {
			Integer index = 0;
			if(pokemon.getNext_evolution() != null) {
				for(NextEvolution newNextEvolution : pokemon.getNext_evolution()) {
					originalPokemon.getNext_evolution().get(index).setName(newNextEvolution.getName());
					originalPokemon.getNext_evolution().get(index).setNum(newNextEvolution.getNum());
					index++;
				}
			}
			
			save(PokemonDTO.convertToDTO(originalPokemon));
			responseMessage = "Pokemon of ID " + id + " updated successfully!";
			return responseMessage;
		}
		return responseMessage;
	}

	public String delete(Long id) {
		Pokemon pokemon = getById(id);
		
		if(pokemon == null) {
			return "This pokemon ID " + id + " doesn't exist";
		} else {
			for (NextEvolution evolution : pokemon.getNext_evolution()) {
				getEvolutionRepository().deleteById(evolution.getId());
			}
			
			getPokemonRepository().deleteById(id);
			return "Pokemon of ID " + id + " removed!";
		}
	}

	public PokemonRepository getPokemonRepository() {
		return pokemonRepository;
	}

	public NextEvolutionRepository getEvolutionRepository() {
		return nextEvolutionRepository;
	}

}
