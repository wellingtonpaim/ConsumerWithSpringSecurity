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
public class PlanetDTO {
	
	private String nome;
	
	private String periodo_rotacao;
	
	private String periodo_orbital;
	
	private String diametro;
	
	private String clima;
	
	private String gravidade;
	
	private String terreno;
	
	private String agua_superficie;
	
	private String populacao;
	
	private List<String> moradores;
	
	private List<String> filmes;
	
	private String criado;
	
	private String editado;
	
	private String url;

}
