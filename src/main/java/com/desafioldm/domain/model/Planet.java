package com.desafioldm.domain.model;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Planet {
	
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
