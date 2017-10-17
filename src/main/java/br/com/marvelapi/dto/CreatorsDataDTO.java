package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class CreatorsDataDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatorsDataDTO {

	/** The creators results DTO. */
	@JsonProperty("data")
	private CreatorsResultsDTO creatorsResultsDTO;

	/**
	 * Gets the creators results DTO.
	 *
	 * @return the creators results DTO
	 */
	public CreatorsResultsDTO getCreatorsResultsDTO() {
		return creatorsResultsDTO;
	}

	/**
	 * Sets the creators results DTO.
	 *
	 * @param creatorsResultsDTO
	 *            the new creators results DTO
	 */
	public void setCreatorsResultsDTO(CreatorsResultsDTO creatorsResultsDTO) {
		this.creatorsResultsDTO = creatorsResultsDTO;
	}

}
