package com.desafioldm.api.controller;

import com.desafioldm.api.exceptionhandler.PlanetNotFoundException;
import com.desafioldm.domain.DTO.PlanetDTO;
import com.desafioldm.domain.model.Planet;
import com.desafioldm.domain.service.PlanetService;
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
@RequestMapping("/planetas")
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @Autowired
    @Qualifier("planetModelMapper")
    private ModelMapper planetModelMapper;

    RestTemplate restTemplate = new RestTemplate();

    @ApiOperation(value = "Retorna uma lista de planetas")
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
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

    @ApiOperation(value = "Retorna um planeta especificado pelo id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public PlanetDTO getPlanetId(@PathVariable Long id) {
        Planet planet = null;

        try {
            return toPlanetDTO(planetService.getPlanetId(id));
        } catch (HttpClientErrorException.NotFound e){
            throw new PlanetNotFoundException(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private PlanetDTO toPlanetDTO(Planet planet) {
        return planetModelMapper.map(planet, PlanetDTO.class);
    }
}
