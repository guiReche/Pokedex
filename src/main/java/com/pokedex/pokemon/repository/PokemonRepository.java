package com.pokedex.pokemon.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.pokedex.pokemon.entity.Pokemon;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Long>, JpaSpecificationExecutor<Pokemon> {

	List<Pokemon> findAll(Specification<Pokemon> specification);
}
