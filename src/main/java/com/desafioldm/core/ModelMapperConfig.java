package com.desafioldm.core;

import com.desafioldm.domain.DTO.FilmDTO;
import com.desafioldm.domain.DTO.PeopleDTO;
import com.desafioldm.domain.DTO.PlanetDTO;
import com.desafioldm.domain.model.Film;
import com.desafioldm.domain.model.People;
import com.desafioldm.domain.model.Planet;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ModelMapperConfig {

    @Bean
	public ModelMapper filmModelMapper() {
        var filmModelMapper = new ModelMapper();

    	filmModelMapper.createTypeMap(Film.class, FilmDTO.class)
    		.addMapping(Film::getTitle, FilmDTO::setTitulo)
    		.addMapping(Film::getEpisode_id, FilmDTO::setEpisodio_id)
    		.addMapping(Film::getOpening_crawl, FilmDTO::setRastreamento_abertura)
    		.addMapping(Film::getDirector, FilmDTO::setDiretor)
    		.addMapping(Film::getProducer, FilmDTO::setProdutor)
    		.addMapping(Film::getRelease_date, FilmDTO::setData_lancamento)
    		.addMapping(Film::getSpecies, FilmDTO::setEspecies)
    		.addMapping(Film::getStarships, FilmDTO::setNaves_estelares)
    		.addMapping(Film::getVehicles, FilmDTO::setVeiculos)
    		.addMapping(Film::getCharacters, FilmDTO::setPersonagens)
    		.addMapping(Film::getPlanets, FilmDTO::setPlanetas)
    		.addMapping(Film::getCreated, FilmDTO::setCriado)
    		.addMapping(Film::getEdited, FilmDTO::setEditado)
    		.addMapping(Film::getUrl, FilmDTO::setUrl);

		return filmModelMapper;

    }

	@Bean
	public ModelMapper peopleModelMapper() {
		var peopleModelMapper = new ModelMapper();

		peopleModelMapper.createTypeMap(People.class, PeopleDTO.class)
				.addMapping(People::getName, PeopleDTO::setNome)
				.addMapping(People::getBirth_year, PeopleDTO::setAno_nascimento)
				.addMapping(People::getEye_color, PeopleDTO::setCor_olhos)
				.addMapping(People::getGender, PeopleDTO::setSexo)
				.addMapping(People::getHair_color, PeopleDTO::setCor_cabelo)
				.addMapping(People::getHeight, PeopleDTO::setAltura)
				.<List<String>>addMapping(People::getSpecies, PeopleDTO::setEspecies)
				.<List<String>>addMapping(People::getStarships, PeopleDTO::setNaves_estelares)
				.<List<String>>addMapping(People::getVehicles, PeopleDTO::setVeiculos)
				.addMapping(People::getMass, PeopleDTO::setMassa)
				.addMapping(People::getSkin_color, PeopleDTO::setCor_pele)
				.addMapping(People::getHomeworld, PeopleDTO::setPlaneta_natal)
				.addMapping(People::getCreated, PeopleDTO::setCriado)
				.addMapping(People::getEdited, PeopleDTO::setEditado)
				.<List<String>>addMapping(People::getFilms, PeopleDTO::setFilmes)
				.addMapping(People::getUrl, PeopleDTO::setUrl);

		return peopleModelMapper;
	}

	@Bean
	public ModelMapper planetModelMapper() {
		var planetModelMapper = new ModelMapper();

		planetModelMapper.createTypeMap(Planet.class, PlanetDTO.class)
				.addMapping(Planet::getName, PlanetDTO::setNome)
				.addMapping(Planet::getDiameter, PlanetDTO::setDiametro)
				.addMapping(Planet::getRotation_period, PlanetDTO::setPeriodo_rotacao)
				.addMapping(Planet::getOrbital_period, PlanetDTO::setPeriodo_orbital)
				.addMapping(Planet::getGravity, PlanetDTO::setGravidade)
				.addMapping(Planet::getPopulation, PlanetDTO::setPopulacao)
				.addMapping(Planet::getClimate, PlanetDTO::setClima)
				.addMapping(Planet::getTerrain, PlanetDTO::setTerreno)
				.addMapping(Planet::getSurface_water, PlanetDTO::setAgua_superficie)
				.addMapping(Planet::getResidents, PlanetDTO::setMoradores)
				.addMapping(Planet::getFilms, PlanetDTO::setFilmes)
				.addMapping(Planet::getCreated, PlanetDTO::setCriado)
				.addMapping(Planet::getEdited, PlanetDTO::setEditado)
				.addMapping(Planet::getUrl, PlanetDTO::setUrl);

		return planetModelMapper;

	}
}
