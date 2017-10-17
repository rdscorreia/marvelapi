package br.com.marvelapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Comics.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)	
public class ComicsDTO {

	/** The Id. */
	@JsonProperty("id")
	private Integer id;

	/** The title. */
	@JsonProperty("title")
	private String title;

	/** The variant description. */
	@JsonProperty("variantDescription")
	private String variantDescription;

	/** The description. */
	@JsonProperty("description")
	private String description;

	/** The creators. */
	@JsonProperty("creators")
	private ComicsCreatorsDTO creators;

	/**
	 * Instantiates a new comics.
	 */
	public ComicsDTO() {
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
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the title.
	 *
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Gets the variant description.
	 *
	 * @return the variant description
	 */
	public String getVariantDescription() {
		return variantDescription;
	}

	/**
	 * Sets the variant description.
	 *
	 * @param variantDescription
	 *            the new variant description
	 */
	public void setVariantDescription(String variantDescription) {
		this.variantDescription = variantDescription;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public ComicsCreatorsDTO getCreators() {
		return creators;
	}

	public void setCreators(ComicsCreatorsDTO creators) {
		this.creators = creators;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComicsDTO other = (ComicsDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Comics [id=" + id + ", title=" + title + ", variantDescription=" + variantDescription + ", description="
				+ description + "]";
	}

}
