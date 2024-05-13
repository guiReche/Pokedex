package com.pokedex.pokemon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pokedex.pokemon.dto.PokemonDTO;
import com.pokedex.pokemon.dto.PokemonFilterDTO;
import com.pokedex.pokemon.entity.Pokemon;
import com.pokedex.pokemon.service.PokemonService;

@RestController
@RequestMapping("/pokedex")
public class PokemonController {
	
	@Autowired
	private PokemonService pokemonService;
	
	@GetMapping(value = "/getAll")
	public @ResponseBody ResponseEntity<?> getAll() {
		try {
			List<PokemonDTO> pokemonListDTO = pokemonService.getAll();
			
			if(!pokemonListDTO.isEmpty()) {
				return new ResponseEntity<>(pokemonListDTO, HttpStatus.OK);
			} else {
				return new ResponseEntity<>("There is no Pokemon registered yet", HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error: " + e.getMessage());
		}
	}
	
	@GetMapping(value = "/getById/{id}")
    public @ResponseBody ResponseEntity<?> getById(@PathVariable Long id) {		
		try {
			Pokemon pokemon = pokemonService.getById(id);
			
	        if (pokemon == null) {
	            return new ResponseEntity<>("Pokemon of ID " + id + " doesn't located or doesn't registered yet", HttpStatus.NOT_FOUND);
	        } else {
				return new ResponseEntity<>(PokemonDTO.convertToDTO(pokemon), HttpStatus.OK);
	        }
		} catch(Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }
	
	@PostMapping(value = "/filter")
	public ResponseEntity<List<Pokemon>> filter(@RequestBody PokemonFilterDTO pokemon) {
		return ResponseEntity.status(HttpStatus.OK).body(pokemonService.findPokemonsByAttributes(pokemon));
	}
	
	@PostMapping(value = "/insert")
    public ResponseEntity<String> insert(@RequestBody PokemonDTO pokemonDTO) {
		try {
			pokemonService.save(pokemonDTO);
			return ResponseEntity.status(HttpStatus.OK).body("Pokemon created successfully!");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed trying to insert new data, error message: " + e.getMessage());
		}
        
	}
	
	@PutMapping(value = "/update/{id}")
	public @ResponseBody ResponseEntity<?> update(@PathVariable Long id, @RequestBody Pokemon pokemon) {
		try {
			String message = pokemonService.update(pokemon, id);
			return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) { 
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error trying to update pokemon. Error message: " + e.getMessage());
		}
	}
	
	@DeleteMapping("/delete")
	public @ResponseBody ResponseEntity<String> delete(@RequestParam Long id) {
		try {
			pokemonService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Pokemon deleted succesfully");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal error, message: " + e.getMessage());
		}	
	}
	

}
