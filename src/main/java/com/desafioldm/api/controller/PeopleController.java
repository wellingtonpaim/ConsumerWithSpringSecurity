package com.desafioldm.api.controller;

import com.desafioldm.api.exceptionhandler.PeopleNotFoundException;
import com.desafioldm.domain.DTO.PeopleDTO;
import com.desafioldm.domain.model.People;
import com.desafioldm.domain.service.PeopleService;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
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

    RestTemplate restTemplate = new RestTemplate();

    @ApiOperation(value = "Retorna uma lista de pessoas")
    @RequestMapping(method = RequestMethod.GET, produces="application/json")
    public List<PeopleDTO> getAllPeoples() throws IOException {

        try {
            return peopleService.getAllPeople()
                    .stream()
                    .map(this::toPeopleDTO)
                    .collect(Collectors.toList());
        } catch (JacksonException e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "Retorna uma pessoa especificada pelo id")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces="application/json")
    public PeopleDTO getFilmId(@PathVariable Long id) {
        People people = null;

        try {
            return toPeopleDTO(peopleService.getPeopleId(id));
        } catch (HttpClientErrorException.NotFound e){
            throw new PeopleNotFoundException(id);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private PeopleDTO toPeopleDTO(People people) {
        return peopleModelMapper.map(people, PeopleDTO.class);
    }
}

