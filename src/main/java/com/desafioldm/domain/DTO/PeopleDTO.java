package com.desafioldm.domain.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PeopleDTO {
	
	private String nome;
	
	private String altura;
	
	private String massa;
	
	private String cor_cabelo;
	
	private String cor_pele;
	
	private String cor_olhos;
	
	private String ano_nascimento;
	
	private String sexo;
	
	private String planeta_natal;
	
	private List<String> filmes;
	
	private List<String> especies;
	
	private List<String> veiculos;
	
	private List<String> naves_estelares;
	
	private String criado;
	
	private String editado;
	
	private String url;

}
