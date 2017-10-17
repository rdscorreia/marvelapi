package br.com.marvelapi.endpoint;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marvelapi.dto.ComicsCreatorsItemsDTO;
import br.com.marvelapi.dto.ComicsDTO;
import br.com.marvelapi.dto.ComicsDataDTO;
import br.com.marvelapi.dto.CreatorsDTO;
import br.com.marvelapi.dto.CreatorsDataDTO;

// TODO: Auto-generated Javadoc
/**
 * The Class ComicsResource.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION)
public class ComicsResource {

	/** The Constant TS. */
	public final static String TS = "1";

	/** The Constant PUBLIC_KEY. */
	public final static CharSequence PUBLIC_KEY = System.getenv("MARVEL_PUBLIC_KEY");

	/** The Constant PRIVATE_KEY. */
	public final static CharSequence PRIVATE_KEY = System.getenv("MARVEL_PRIVATE_KEY");

	/** The Constant BASE_URL. */
	private final static String BASE_URI = "http://gateway.marvel.com";

	/** The Constant COMICS_URL. */
	private final static String COMICS_RESOURCE = "/v1/public/comics";

	/** The Constant COMICS_CREATOR_RESOURCE. */
	private final static String COMICS_CREATOR_RESOURCE = "/creators";
	
	/** The Constant CREATORS_RESOURCE. */
	private final static String CREATORS_RESOURCE = "/v1/public/creators";
		
	/**
	 * Instantiates a new comics resource.
	 */
	public ComicsResource() {
	}

	
	/**
	 * Gera hash.
	 *
	 * @return the string
	 */
	public static String geraHash() {
		String hashKey = null;
		try {
			final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			String key = TS + PRIVATE_KEY + PUBLIC_KEY;
			hashKey = new HexBinaryAdapter().marshal(messageDigest.digest(key.getBytes())).toLowerCase();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return hashKey;

	}


	/**
	 * Gets the comics.
	 *
	 * @return the comics
	 */
	public List<ComicsDTO> getComics()  {

		Client client = ClientBuilder.newClient();

		/*
		 * WebTarget webtarget = client.target(BASE_URI);
		 * 
		 * WebTarget resourceWebTarget = webtarget.path(COMICS_RESOURCE); 
		 * WebTarget resourceWebTargetWithQueryParam = resourceWebTarget.queryParam("ts", TS)
		 * 																.queryParam("apikey", PUBLIC_KEY)
		 * 																.queryParam("hash", geraHash());
		 * System.out.println("Endpoint gerado com parametros: " + resourceWebTargetWithQueryParam);
		 * Da pra devolver direto sem usar o Invocation Builder ou com o
		 * InvocationBuilder Response response = resourceWebTargetWithQueryParam
		 * 												.request(MediaType.TEXT_PLAIN_TYPE)
		 * 												.get();
		 * Invocation.Builder invocationBuilder = resourceWebTargetWithQueryParam
		 * 												.request(MediaType.TEXT_PLAIN_TYPE);
		 * Response response = invocationBuilder.get();
		 */

		/*
		 * Usando no estilo de API fluente
		 * Response response = client.target(BASE_URI)
		 *			.path(COMICS_RESOURCE) 
		 *			.queryParam("ts", TS) 
		 *			.queryParam("apikey", PUBLIC_KEY) 
		 *			.queryParam("hash", geraHash()) 
		 *			.request(MediaType.TEXT_PLAIN)
		 * 			.get(); 
		 * System.out.println(response.getStatus()); 
		 * String entity =  response.readEntity(String.class); 
		 * System.out.println(entity);
		 */
		
		/*
		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.TEXT_PLAIN)
				.get(String.class);
		System.out.println(response);		
		JsonNode nameNode = parseJson(response);
		List<ComicsDTO> comics = converterJsonDTOComics(nameNode);
		*/
		
		ComicsDataDTO comicsDataDTO =  client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.APPLICATION_JSON_TYPE)
				.get()
				.readEntity(ComicsDataDTO.class);

		extractCodigoCreator(comicsDataDTO);

		return comicsDataDTO.getComicResultsDTO().getComicsDTO();

	}


	/**
	 * Extract codigo creator.
	 * 
	 * Itera as urls para poder pegar somente o código do Creator
	 *
	 * @param comicsDataDTO the comics data DTO
	 */
	private void extractCodigoCreator(ComicsDataDTO comicData) {
		
		for (ComicsDTO comic : comicData.getComicResultsDTO().getComicsDTO()) {
			for (ComicsCreatorsItemsDTO itemCreator : comic.getCreators().getItems()) {				
				String[] linkSplit = itemCreator.getResourceURI().split("/");				
				itemCreator.setResourceURI(linkSplit[linkSplit.length -1]);
			}
		}

	}

	
	/**
	 * Gets the comics id.
	 *
	 * @param id the id
	 * @return the comics id
	 */
	public ComicsDTO getComicsId(Integer id)  {

		Client client = ClientBuilder.newClient();

		ComicsDataDTO comicsDataDTO = client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.queryParam("id", id)
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(ComicsDataDTO.class);

		return comicsDataDTO.getComicResultsDTO().getComicsDTO().get(0);

	}


	/**
	 * Gets the comic creators.
	 *
	 * @param id the id
	 * @return the comic creators
	 */
	public Map<String, Object> getComicCreators(Integer id) {

		ComicsDTO comics = getComicsId(id);

		Client client = ClientBuilder.newClient();

		CreatorsDataDTO creatorsData = client.target(BASE_URI)
				.path(COMICS_RESOURCE + "/" + comics.getId() + COMICS_CREATOR_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(CreatorsDataDTO.class);

		Map<String, Object> mapComicListCreator = new HashMap<>();
		
		mapComicListCreator.put("comic", comics);
		mapComicListCreator.put("creator", creatorsData.getCreatorsResultsDTO().getCreatorsDTO());

		//JsonNode nameNode = parseJson(response);

		//List<CreatorsDTO> creators = converterJsonDTOCreators(nameNode);

		//comics.setCreators(creators);

		return mapComicListCreator;

	}
	
	/**
	 * Gets the creator id.
	 *
	 * @param resourceURI the resource URI
	 * @return the creator id
	 */
	public CreatorsDTO getCreatorId(String resourceURI) {

		Client client = ClientBuilder.newClient();

		
		CreatorsDataDTO	 creatorsDataDTO = client.target(BASE_URI)
					.path(CREATORS_RESOURCE)
					.path(resourceURI)
					.queryParam("ts", TS)
					.queryParam("apikey", PUBLIC_KEY)
					.queryParam("hash", geraHash())
					.request(MediaType.APPLICATION_JSON)
					.get()
					.readEntity(CreatorsDataDTO.class);
			
		return creatorsDataDTO.getCreatorsResultsDTO().getCreatorsDTO().get(0);
	}

	/**
	 * Parses the json.
	 *
	 * @param response the response
	 * @return the json node
	 */
	public JsonNode parseJson(String response) {

		ObjectMapper mapper = new ObjectMapper();

		mapper.setSerializationInclusion(Include.NON_NULL);

		JsonNode rootNode;
		JsonNode nameNode = null;

		try {
			rootNode = mapper.readTree(response);
			nameNode = rootNode.get("data").get("results");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return nameNode;
	}


	/**
	 * Converter json DTO creators.
	 *
	 * @param nameNode the name node
	 * @return the list
	 */
	private List<CreatorsDTO> converterJsonDTOCreators(JsonNode nameNode) {

		List<CreatorsDTO> creators = new ArrayList<CreatorsDTO>();

		for (int i = 0; i < nameNode.size(); i++) {

			CreatorsDTO creator = new CreatorsDTO();

			if (!nameNode.get(i).get("id").isNull()) {
				creator.setId((Integer) nameNode.get(i).get("id").numberValue());
			}

			if (!nameNode.get(i).get("fullName").isNull()) {
				creator.setFullName(nameNode.get(i).get("fullName").textValue());
			}

			creators.add(creator);

		}

		return creators;
	}


	/**
	 * Converter json DTO comics.
	 *
	 * @param nameNode the name node
	 * @return the list
	 */
	public List<ComicsDTO> converterJsonDTOComics(JsonNode nameNode) {

		List<ComicsDTO> comics = new ArrayList<ComicsDTO>();

		for (int i = 0; i < nameNode.size(); i++) {

			ComicsDTO comic = new ComicsDTO();

			if (!nameNode.get(i).get("id").isNull()) {
				comic.setId((Integer) nameNode.get(i).get("id").numberValue());
			}
			if (!nameNode.get(i).get("title").isNull()) {
				comic.setTitle(nameNode.get(i).get("title").textValue());
			}
			if (!nameNode.get(i).get("description").isNull()) {
				comic.setDescription(nameNode.get(i).get("description").textValue());
			}
			if (!nameNode.get(i).get("variantDescription").isNull()) {
				comic.setVariantDescription(nameNode.get(i).get("variantDescription").textValue());
			}
			
			comics.add(comic);
		}

		return comics;

	}


	

}