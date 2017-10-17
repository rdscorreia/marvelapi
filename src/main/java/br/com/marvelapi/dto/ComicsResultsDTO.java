package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsResultsDTO {

	@JsonProperty("results")
	private List<ComicsDTO> comicsDTO;

	public List<ComicsDTO> getComicsDTO() {
		return comicsDTO;
	}

	public void setComicsDTO(List<ComicsDTO> comicsDTO) {
		this.comicsDTO = comicsDTO;
	}

}
