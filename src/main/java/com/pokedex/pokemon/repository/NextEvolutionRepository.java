package com.pokedex.pokemon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pokedex.pokemon.entity.NextEvolution;

@Repository
public interface NextEvolutionRepository extends JpaRepository<NextEvolution, Long> {

}
