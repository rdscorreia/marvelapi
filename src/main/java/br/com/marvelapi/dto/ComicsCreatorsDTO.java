package br.com.marvelapi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class ComicsCreatorsDTO {

	@JsonProperty("available")
	private Integer available;

	@JsonProperty("returned")
	private Integer returned;

	@JsonProperty("collectionURI")
	private String collectionURI;

	@JsonProperty("items")
	private List<ComicsCreatorsItemsDTO> items;

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getReturned() {
		return returned;
	}

	public void setReturned(Integer returned) {
		this.returned = returned;
	}

	public String getCollectionURI() {
		return collectionURI;
	}

	public void setCollectionURI(String collectionURI) {
		this.collectionURI = collectionURI;
	}

	public List<ComicsCreatorsItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<ComicsCreatorsItemsDTO> items) {
		this.items = items;
	}

}
