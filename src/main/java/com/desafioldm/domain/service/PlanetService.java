package com.desafioldm.domain.service;

import com.desafioldm.domain.model.Planet;
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
public class PlanetService {

    @Autowired
    private WebClient webclient;

    ObjectMapper mapper = new ObjectMapper();

    String RESULT_FIELD = "results";

    public List<Planet> getAllPlanets() throws IOException {

        String response = this.webclient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path("planets/").build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        JsonNode node = mapper.readValue(response, JsonNode.class);

        if (node.isObject()) {
            ObjectNode obj = mapper.convertValue(node, ObjectNode.class);
            if (obj.has(RESULT_FIELD)) {
                JsonNode listPlanet = obj.get(RESULT_FIELD);
                ObjectReader reader = mapper.readerFor(new TypeReference<List<Planet>>() {
                });
                List<Planet> planetList = reader.readValue(listPlanet);
                return planetList;
            }
        }
        return null;
    }

    public Planet getPlanetId(Long id) throws JsonProcessingException ,
            HttpClientErrorException.NotFound {

        Mono<Planet> planetMono = this.webclient
                .method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path("planets/{id}").build(id))
                .retrieve()
                .bodyToMono(Planet.class);

        Planet planet = planetMono.block();
        return planet;
    }
}
