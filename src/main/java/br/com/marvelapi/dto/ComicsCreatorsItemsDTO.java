package br.com.marvelapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class ComicsCreatorsItemsDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComicsCreatorsItemsDTO {

	/** The resource URI. */
	@JsonProperty("resourceURI")
	private String resourceURI;

	/** The name. */
	@JsonProperty("name")
	private String name;

	/** The role. */
	@JsonProperty("role")
	private String role;

	/**
	 * Gets the resource URI.
	 *
	 * @return the resource URI
	 */
	public String getResourceURI() {
		return resourceURI;
	}

	/**
	 * Sets the resource URI.
	 *
	 * @param resourceURI the new resource URI
	 */
	public void setResourceURI(String resourceURI) {
		this.resourceURI = resourceURI;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

}
