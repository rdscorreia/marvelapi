package br.com.marvelapi.endpoint;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.marvelapi.model.Comics;
import br.com.marvelapi.model.Creators;

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
	 * Gera hash.
	 *
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
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
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws JsonProcessingException
	 *             the json processing exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public JsonNode getComics() throws NoSuchAlgorithmException, JsonProcessingException, IOException {

		Client client = ClientBuilder.newClient();

		/*
		 * WebTarget webtarget = client.target(BASE_URI);
		 * 
		 * WebTarget resourceWebTarget = webtarget.path(COMICS_RESOURCE); WebTarget
		 * resourceWebTargetWithQueryParam = resourceWebTarget.queryParam("ts", TS)
		 * .queryParam("apikey", PUBLIC_KEY) .queryParam("hash", geraHash());
		 * 
		 * System.out.println("Endpoint gerado com parametros: " +
		 * resourceWebTargetWithQueryParam);
		 * 
		 * Da pra devolver direto sem usar o Invocation Builder ou com o
		 * InvocationBuilder Response response =
		 * resourceWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE).get();
		 * Invocation.Builder invocationBuilder =
		 * resourceWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);
		 * 
		 * Response response = invocationBuilder.get();
		 * 
		 * /* Usando no estilo de API fluente
		 */

		/*
		 * Response String response = client.target(BASE_URI)
		 * 
		 * .path(COMICS_RESOURCE) .queryParam("ts", TS) .queryParam("apikey",
		 * PUBLIC_KEY) .queryParam("hash", geraHash()) .request(MediaType.TEXT_PLAIN)
		 * .get();
		 * 
		 * 
		 * System.out.println(response.getStatus()); String entity =
		 * response.readEntity(String.class); System.out.println(entity);
		 * 
		 */

		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.TEXT_PLAIN).get(String.class);

		System.out.println(response);

		return parseJson(response);
	}

	/**
	 * Gets the comics id.
	 *
	 * @param id
	 *            the id
	 * @return the comics id
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 * @throws JsonProcessingException
	 *             the json processing exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public Comics getComicsId(Integer id) throws NoSuchAlgorithmException, JsonProcessingException, IOException {

		Client client = ClientBuilder.newClient();

		/*
		 * Response response = client.target(BASE_URI) 
		 * 		.path(COMICS_RESOURCE)
		 * 		.queryParam("ts", TS) 
		 * 		.queryParam("apikey", PUBLIC_KEY) 
		 * 		.queryParam("hash", geraHash()) 
		 * 		.queryParam("id", id) 
		 * 		.request(MediaType.APPLICATION_JSON)
		 * 		.get();
		 * 
		 * System.out.println(response.getStatus()); String entity =
		 * response.readEntity(String.class); 
		 * System.out.println(entity); 
		 * return entity;
		 */

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

		Comics comics = converterJsonDTOComics(nameNode);

		return comics;

	}

	/**
	 * Gets the comics creator.
	 *
	 * @param id
	 *            the id
	 * @return the comics creator
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public Comics getComicCreators(Integer id) throws NoSuchAlgorithmException, JsonProcessingException, IOException {

		Comics comics = getComicsId(id);

		Client client = ClientBuilder.newClient();

		String response = client.target(BASE_URI)
				.path(COMICS_RESOURCE + "/" + comics.getId() + COMICS_CREATOR_RESOURCE)
				.queryParam("ts", TS)
				.queryParam("apikey", PUBLIC_KEY)
				.queryParam("hash", geraHash())
				.request(MediaType.APPLICATION_JSON)
				.get()
				.readEntity(String.class);

		System.out.println(response);

		JsonNode nameNode = parseJson(response);

		List<Creators> creators = converterJsonDTOCreators(nameNode);

		comics.setCreators(creators);

		return comics;

	}

	/**
	 * Parses the json.
	 *
	 * @param response
	 *            the response
	 * @return the json node
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws JsonProcessingException
	 *             the json processing exception
	 */
	public JsonNode parseJson(String response) throws IOException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(response);
		JsonNode nameNode = rootNode.get("data").get("results");
		return nameNode;
	}

	/**
	 * Converter json DTO creators.
	 *
	 * @param nameNode
	 *            the name node
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
	 * @param nameNode
	 *            the name node
	 * @return the comics
	 */
	public Comics converterJsonDTOComics(JsonNode nameNode) {
		Comics comics = new Comics();

		if (!nameNode.get(0).get("id").isNull()) {
			comics.setId((Integer) nameNode.get(0).get("id").numberValue());
		}
		if (!nameNode.get(0).get("title").isNull()) {
			comics.setTitle(nameNode.get(0).get("title").textValue());
		}
		if (!nameNode.get(0).get("description").isNull()) {
			comics.setDescription(nameNode.get(0).get("description").textValue());
		}
		if (!nameNode.get(0).get("variantDescription").isNull()) {
			comics.setVariantDescription(nameNode.get(0).get("variantDescription").textValue());
		}
		return comics;
	}

}