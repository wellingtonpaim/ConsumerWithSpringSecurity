package com.desafioldm.api.controller;

import com.desafioldm.api.exceptionhandler.PeopleNotFoundException;
import com.desafioldm.domain.DTO.PeopleDTO;
import com.desafioldm.domain.model.People;
import com.desafioldm.domain.service.PeopleService;
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
@RequestMapping("/pessoas")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @Autowired
    @Qualifier("peopleModelMapper")
    private ModelMapper peopleModelMapper;

    String url = "https://swapi.dev/api/people/";
    RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public List<PeopleDTO> getAllPeoples() throws IOException {
        try {
            return peopleService.getAllPeoples()
                    .stream()
                    .map(this::toPeopleDTO)
                    .collect(Collectors.toList());
        } catch (JacksonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/{id}")
    public PeopleDTO getFilmId(@PathVariable String id) {
        People people = null;
        Long peopleId = Long.parseLong(id);
        try {
            return toPeopleDTO(peopleService.getPeopleId(id));
        } catch (HttpClientErrorException.NotFound e){
            throw new PeopleNotFoundException(peopleId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private PeopleDTO toPeopleDTO(People people) {
        return peopleModelMapper.map(people, PeopleDTO.class);
    }
}

