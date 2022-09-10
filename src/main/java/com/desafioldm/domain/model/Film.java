package com.desafioldm.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Film {

	private String title;
	
	private String episode_id;
	
	private String opening_crawl;
	
	private String director;
	
	private String producer;
	
	private String release_date;
	
	private List<String> characters;
	
	private List<String> planets;
	
	private List<String> starships;
	
	private List<String> vehicles;
	
	private List<String> species;
	
	private String created;
	
	private String edited;
	
	private String url;

}
