package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class ComicsCreatorsDTO.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class ComicsCreatorsDTO {

	/** The available. */
	@JsonProperty("available")
	private Integer available;

	/** The returned. */
	@JsonProperty("returned")
	private Integer returned;

	/** The collection URI. */
	@JsonProperty("collectionURI")
	private String collectionURI;

	/** The items. */
	@JsonProperty("items")
	private List<ComicsCreatorsItemsDTO> items;

	/**
	 * Gets the available.
	 *
	 * @return the available
	 */
	public Integer getAvailable() {
		return available;
	}

	/**
	 * Sets the available.
	 *
	 * @param available the new available
	 */
	public void setAvailable(Integer available) {
		this.available = available;
	}

	/**
	 * Gets the returned.
	 *
	 * @return the returned
	 */
	public Integer getReturned() {
		return returned;
	}

	/**
	 * Sets the returned.
	 *
	 * @param returned the new returned
	 */
	public void setReturned(Integer returned) {
		this.returned = returned;
	}

	/**
	 * Gets the collection URI.
	 *
	 * @return the collection URI
	 */
	public String getCollectionURI() {
		return collectionURI;
	}

	/**
	 * Sets the collection URI.
	 *
	 * @param collectionURI the new collection URI
	 */
	public void setCollectionURI(String collectionURI) {
		this.collectionURI = collectionURI;
	}

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<ComicsCreatorsItemsDTO> getItems() {
		return items;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(List<ComicsCreatorsItemsDTO> items) {
		this.items = items;
	}

}
