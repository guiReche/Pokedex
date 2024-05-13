package com.pokedex.pokemon.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.pokedex.pokemon.dto.PokemonFilterDTO;
import com.pokedex.pokemon.entity.Pokemon;

import jakarta.persistence.criteria.Predicate;

public class PokemonSpecifications {

	public static Specification<Pokemon> withAttributes(PokemonFilterDTO pokemon) {
		return (root, query, builder) -> {
			List<Predicate> predicates = new ArrayList<>();
			
			if(pokemon.getId() != null) {
				predicates.add(builder.equal(root.get("id"), pokemon.getId()));
			}
			
			if(pokemon.getNum() != null) {
				predicates.add(builder.equal(root.get("number"), pokemon.getNum()));
			}

			if(pokemon.getName() != null) {
				predicates.add(builder.equal(root.get("name"), pokemon.getName()));
			}
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
