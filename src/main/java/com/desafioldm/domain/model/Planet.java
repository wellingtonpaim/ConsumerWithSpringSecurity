package com.desafioldm.domain.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Planet {
	
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String rotation_period;
	
	private String orbital_period;
	
	private String diameter;
	
	private String climate;
	
	private String gravity;
	
	private String terrain;
	
	private String surface_water;
	
	private String population;
	
	private List<String> residents;
	
	private List<String> films;
	
	private String created;
	
	private String edited;
	
	private String url;

}
