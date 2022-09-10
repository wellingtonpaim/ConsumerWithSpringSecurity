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
public class FilmDTO {

	private String titulo;
	
	private String episodio_id;
	
	private String rastreamento_abertura;
	
	private String diretor;
	
	private String produtor;
	
	private String data_lancamento;
	
	private List<String> personagens;
	
	private List<String> planetas;
	
	private List<String> naves_estelares;
	
	private List<String> veiculos;
	
	private List<String> especies;
	
	private String criado;
	
	private String editado;
	
	private String url;

}
