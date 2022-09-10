package com.desafioldm.domain.service;

import com.desafioldm.domain.model.People;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class PeopleService {

    String url = "https://swapi.dev/api/people/";
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();

    public List<People> getAllPeoples() throws IOException {

        String result = restTemplate.getForObject(url,String.class);
        String RESULT_FIELD = "results";

        JsonNode node = mapper.readValue(result, JsonNode.class);

        if (node.isObject()) {
            ObjectNode obj = mapper.convertValue(node, ObjectNode.class);
            if (obj.has(RESULT_FIELD)) {
                JsonNode listPeople = obj.get(RESULT_FIELD);

                ObjectReader reader = mapper.readerFor(new TypeReference<List<People>>() {
                });

                List<People> peopleList = reader.readValue(listPeople);

                return peopleList;
            }
        }
        return null;
    }

    public People getPeopleId(String id) throws JsonProcessingException,
            HttpClientErrorException.NotFound {
        String urlId = url.concat(id);
        ObjectReader reader = mapper.readerFor(new TypeReference<People>() {});
        String peopleConsumed = restTemplate.getForObject(urlId, String.class);
        People people = reader.readValue(peopleConsumed);

        return people;
    }

}
