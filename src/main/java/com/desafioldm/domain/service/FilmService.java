package com.desafioldm.domain.service;

import com.desafioldm.domain.model.Film;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Service
public class FilmService {

	@Autowired
	private WebClient webclient;

	ObjectMapper mapper = new ObjectMapper();

	String RESULT_FIELD = "results";

	public List<Film> getAllFilms() throws IOException {

		String response = this.webclient
				.method(HttpMethod.GET)
				.uri(uriBuilder -> uriBuilder.path("films/").build())
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(String.class)
				.block();

		JsonNode node = mapper.readValue(response, JsonNode.class);

		if (node.isObject()) {
			ObjectNode obj = mapper.convertValue(node, ObjectNode.class);
			if (obj.has(RESULT_FIELD)) {
				JsonNode listFilm = obj.get(RESULT_FIELD);
				ObjectReader reader = mapper.readerFor(new TypeReference<List<Film>>() {
				});
				List<Film> filmList = reader.readValue(listFilm);
				return filmList;
			}
		}
		return null;
	}

	public Film getFilmId(Long id) throws JsonProcessingException ,
			HttpClientErrorException.NotFound {

		Mono<Film> filmMono = this.webclient
				.method(HttpMethod.GET)
				.uri(uriBuilder -> uriBuilder.path("films/{id}").build(id))
				.retrieve()
				.bodyToMono(Film.class);

		Film film = filmMono.block();
		return film;
	}
}