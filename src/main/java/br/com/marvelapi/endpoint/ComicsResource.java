package br.com.marvelapi.endpoint;

import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

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

import br.com.marvelapi.model.Comics;
import br.com.marvelapi.model.Creators;

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
	public List<Comics> getComics()  {

		Client client = ClientBuilder.newClient();

		/*
		 * WebTarget webtarget = client.target(BASE_URI);
		 * 
		 * WebTarget resourceWebTarget = webtarget.path(COMICS_RESOURCE); 
		 * WebTarget resourceWebTargetWithQueryParam = resourceWebTarget.queryParam("ts", TS)
		 * 																.queryParam("apikey", PUBLIC_KEY)
		 * 																.queryParam("hash", geraHash());
		 * 
		 * System.out.println("Endpoint gerado com parametros: " + resourceWebTargetWithQueryParam);
		 * 
		 * Da pra devolver direto sem usar o Invocation Builder ou com o
		 * InvocationBuilder Response response = resourceWebTargetWithQueryParam
		 * 												.request(MediaType.TEXT_PLAIN_TYPE)
		 * 												.get();
		 * Invocation.Builder invocationBuilder = resourceWebTargetWithQueryParam
		 * 												.request(MediaType.TEXT_PLAIN_TYPE);
		 * 
		 * Response response = invocationBuilder.get();
		 */

		/*
		 * Usando no estilo de API fluente
		 * 
		 * Response response = client.target(BASE_URI)
		 *			.path(COMICS_RESOURCE) 
		 *			.queryParam("ts", TS) 
		 *			.queryParam("apikey", PUBLIC_KEY) 
		 *			.queryParam("hash", geraHash()) 
		 *			.request(MediaType.TEXT_PLAIN)
		 * 			.get(); 
		 * 
		 * System.out.println(response.getStatus()); 
		 * String entity =  response.readEntity(String.class); 
		 * System.out.println(entity);
		 * 
		 */

		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.TEXT_PLAIN).get(String.class);

		System.out.println(response);		

		JsonNode nameNode = parseJson(response);

		List<Comics> comics = converterJsonDTOComics(nameNode);

		return comics;
	}

	
	/**
	 * Gets the comics id.
	 *
	 * @param id the id
	 * @return the comics id
	 */
	public List<Comics> getComicsId(Integer id)  {

		Client client = ClientBuilder.newClient();

		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.queryParam("id", id)
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(String.class);

		System.out.println(response);

		JsonNode nameNode = parseJson(response);

		List<Comics> comics = converterJsonDTOComics(nameNode);

		return comics;

	}


	/**
	 * Gets the comic creators.
	 *
	 * @param id the id
	 * @return the comic creators
	 */
	public List<Comics> getComicCreators(Integer id) {

		List<Comics> comics = getComicsId(id);

		Client client = ClientBuilder.newClient();

		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE + "/" + comics.get(0).getId() + COMICS_CREATOR_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(String.class);

		System.out.println(response);

		JsonNode nameNode = parseJson(response);

		List<Creators> creators = converterJsonDTOCreators(nameNode);

		comics.get(0).setCreators(creators);

		return comics;

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
	private List<Creators> converterJsonDTOCreators(JsonNode nameNode) {

		List<Creators> creators = new ArrayList<Creators>();

		for (int i = 0; i < nameNode.size(); i++) {

			Creators creator = new Creators();

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
	public List<Comics> converterJsonDTOComics(JsonNode nameNode) {

		List<Comics> comics = new ArrayList<Comics>();

		for (int i = 0; i < nameNode.size(); i++) {

			Comics comic = new Comics();

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