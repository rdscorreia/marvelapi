package br.com.marvelapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsDataDTO {

	@JsonProperty("data")
	private ComicsResultsDTO comicResultsDTO;

	public ComicsResultsDTO getComicResultsDTO() {
		return comicResultsDTO;
	}

	public void setComicResultsDTO(ComicsResultsDTO comicResultsDTO) {
		this.comicResultsDTO = comicResultsDTO;
	}

}
