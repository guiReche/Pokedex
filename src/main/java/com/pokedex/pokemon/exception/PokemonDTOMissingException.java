package com.pokedex.pokemon.exception;

public class PokemonDTOMissingException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PokemonDTOMissingException() {
		super("POKEMON CANNOT BE NULL");
	}
	
	public PokemonDTOMissingException(String errorMessage) {
		super(errorMessage);
	}

}
