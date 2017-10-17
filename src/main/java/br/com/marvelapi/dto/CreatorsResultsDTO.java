package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CreatorsResultsDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatorsResultsDTO {

	/** The creators DTO. */
	@JsonProperty("results")
	private List<CreatorsDTO> creatorsDTO;

	/**
	 * Gets the creators DTO.
	 *
	 * @return the creators DTO
	 */
	public List<CreatorsDTO> getCreatorsDTO() {
		return creatorsDTO;
	}

	/**
	 * Sets the creators DTO.
	 *
	 * @param creatorsDTO the new creators DTO
	 */
	public void setCreatorsDTO(List<CreatorsDTO> creatorsDTO) {
		this.creatorsDTO = creatorsDTO;
	}

}
