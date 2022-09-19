package com.desafioldm.api.controller;

import com.desafioldm.api.exceptionhandler.FilmNotFoundException;
import com.desafioldm.domain.DTO.FilmDTO;
import com.desafioldm.domain.model.Film;
import com.desafioldm.domain.service.FilmService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmController {

	@Autowired
	private FilmService filmService;
	
	@Autowired
	@Qualifier("filmModelMapper")
	private ModelMapper filmModelMapper;
	
	RestTemplate restTemplate = new RestTemplate();

	@ApiOperation(value = "Retorna uma lista de filmes")
	@RequestMapping(method = RequestMethod.GET, produces="application/json")
	public List<FilmDTO> getAllFilms() throws IOException {

		try {
			return filmService.getAllFilms()
					.stream()
					.map(this::toFilmDTO)
					.collect(Collectors.toList());
		} catch (JacksonException e) {
			e.printStackTrace();
		}
		return null;
	}

	@ApiOperation(value = "Retorna um filme especificado pelo id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
	public FilmDTO getFilmId(@PathVariable Long id) {
		Film film = null;
//		Long filmId = Long.parseLong(id);

		try {
			return toFilmDTO(filmService.getFilmId(id));
		} catch (HttpClientErrorException.NotFound e){
			throw new FilmNotFoundException(id);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	private FilmDTO toFilmDTO(Film film) {
		return filmModelMapper.map(film, FilmDTO.class);
	}
}
