package com.pokedex.pokemon.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_POKEMON")
public class Pokemon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "number", unique = true, nullable = false)
	private String number;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Lob
	@Column(name = "image")
	private String img;

	@Column(name = "type")
	private List<String> type;
	
	@Column(name = "weight")
	private String weight;
	
	@Column(name = "weaknesses")
	private List<String> weaknesses;
	
	@OneToMany(mappedBy = "pokemon", cascade= CascadeType.ALL)
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

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public List<String> getWeaknesses() {
		return weaknesses;
	}

	public void setWeaknesses(List<String> weaknesses) {
		this.weaknesses = weaknesses;
	}

	public List<NextEvolution> getNext_evolution() {
		return next_evolution;
	}

	public void setNext_evolution(List<NextEvolution> next_evolution) {
		this.next_evolution = next_evolution;
	}
	
}
