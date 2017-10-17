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

}
