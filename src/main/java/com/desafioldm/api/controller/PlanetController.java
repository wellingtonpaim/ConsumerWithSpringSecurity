package com.desafioldm.api.controller;

import com.desafioldm.api.exceptionhandler.FilmNotFoundException;
import com.desafioldm.api.exceptionhandler.PlanetNotFoundException;
import com.desafioldm.domain.DTO.FilmDTO;
import com.desafioldm.domain.DTO.PlanetDTO;
import com.desafioldm.domain.model.Film;
import com.desafioldm.domain.model.Planet;
import com.desafioldm.domain.service.FilmService;
import com.desafioldm.domain.service.PlanetService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/planetas")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @Autowired
    @Qualifier("planetModelMapper")
    private ModelMapper planetModelMapper;

    String url = "https://swapi.dev/api/planets/";
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<PlanetDTO> getAllPlanets() throws IOException {
        try {
            return planetService.getAllPlanets()
                    .stream()
                    .map(this::toPlanetDTO)
                    .collect(Collectors.toList());
        } catch (JacksonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public PlanetDTO getPlanetId(@PathVariable String id) {
        Planet planet = null;
        Long planetId = Long.parseLong(id);
        try {
            return toPlanetDTO(planetService.getPlanetId(id));
        } catch (HttpClientErrorException.NotFound e){
            throw new PlanetNotFoundException(planetId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private PlanetDTO toPlanetDTO(Planet planet) {
        return planetModelMapper.map(planet, PlanetDTO.class);
    }
}
