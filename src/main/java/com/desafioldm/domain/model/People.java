package com.desafioldm.domain.model;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class People<P> {
	
	private String name;
	
	private String height;
	
	private String mass;
	
	private String hair_color;
	
	private String skin_color;
	
	private String eye_color;
	
	private String birth_year;
	
	private String gender;
	
	private String homeworld;
	
	private List<String> films;
	
	private List<String> species;
	
	private List<String> vehicles;
	
	private List<String> starships;
	
	private String created;
	
	private String edited;
	
	private String url;

}
