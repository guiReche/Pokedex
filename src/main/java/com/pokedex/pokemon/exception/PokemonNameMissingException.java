package com.pokedex.pokemon.exception;

public class PokemonNameMissingException extends RuntimeException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokemonNameMissingException() {
		super("NAME CANNOT BE NULL");
	}
	
	public PokemonNameMissingException(String errorMessage) {
		super(errorMessage);
	}
	
}
