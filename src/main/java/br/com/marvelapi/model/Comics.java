package br.com.marvelapi.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

// TODO: Auto-generated Javadoc
/**
 * The Class Comics.
 */
@JsonInclude(Include.NON_EMPTY)	
public class Comics {

	/** The Id. */
	private Integer Id;

	/** The title. */
	private String title;

	/** The variant description. */
	private String variantDescription;

	/** The description. */
	private String description;

	/** The creators. */
	private List<Creators> creators;

	/**
	 * Instantiates a new comics.
	 */
	public Comics() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Integer id) {
		Id = id;
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

	/**
	 * Gets the creators.
	 *
	 * @return the creators
	 */
	public List<Creators> getCreators() {
		return creators;
	}

	/**
	 * Sets the creators.
	 *
	 * @param creators
	 *            the new creators
	 */
	public void setCreators(List<Creators> creators) {
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
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Comics other = (Comics) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
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
		return "Comics [Id=" + Id + ", title=" + title + ", variantDescription=" + variantDescription + ", description="
				+ description + "]";
	}

}
