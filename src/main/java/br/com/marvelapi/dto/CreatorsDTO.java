package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class CreatorsDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class CreatorsDTO {

	/** The id. */
	@JsonProperty("id")
	private Integer id;

	/** The full name. */
	@JsonProperty("fullName")
	private String fullName;

	/** The urls. */
	@JsonProperty("urls")
	private List<Urls> urls;

	/**
	 * Instantiates a new creators DTO.
	 */
	public CreatorsDTO() {

	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the full name.
	 *
	 * @return the full name
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name.
	 *
	 * @param fullName the new full name
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets the urls.
	 *
	 * @return the urls
	 */
	public List<Urls> getUrls() {
		return urls;
	}

	/**
	 * Sets the urls.
	 *
	 * @param urls the new urls
	 */
	public void setUrls(List<Urls> urls) {
		this.urls = urls;
	}

}
