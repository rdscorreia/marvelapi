package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class ComicsResultsDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsResultsDTO {

	/** The comics DTO. */
	@JsonProperty("results")
	private List<ComicsDTO> comicsDTO;

	/**
	 * Gets the comics DTO.
	 *
	 * @return the comics DTO
	 */
	public List<ComicsDTO> getComicsDTO() {
		return comicsDTO;
	}

	/**
	 * Sets the comics DTO.
	 *
	 * @param comicsDTO the new comics DTO
	 */
	public void setComicsDTO(List<ComicsDTO> comicsDTO) {
		this.comicsDTO = comicsDTO;
	}

}
