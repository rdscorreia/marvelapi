package br.com.marvelapi.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

/**
 * The Class Test.
 */
public class Test {

	/** The Constant TS. */
	public final static String TS = "1";

	/** The Constant PUBLIC_KEY. */
	public final static String PUBLIC_KEY = System.getenv("MARVEL_PUBLIC_KEY");

	/** The Constant PRIVATE_KEY. */
	public final static String PRIVATE_KEY = System.getenv("MARVEL_PRIVATE_KEY");

	/** The Constant BASE_URL. */
	private final static String BASE_URI = "http://gateway.marvel.com";

	/** The Constant COMICS_URL. */
	private final static String COMICS_RESOURCE = "/v1/public/comics";
	
	//private final WebTarget webTarget;

	/**
	 * Gera hash.
	 *
	 * @return the string
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	public static String geraHash() throws NoSuchAlgorithmException {
		final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		String key = TS + PRIVATE_KEY + PUBLIC_KEY;
		String hashKey = new HexBinaryAdapter().marshal(messageDigest.digest(key.getBytes()));
		hashKey = hashKey.toLowerCase();
		return hashKey;

	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws NoSuchAlgorithmException
	 *             the no such algorithm exception
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {

		System.out.println("Gerando chave Hash: " + geraHash());

		//Alterar para quando chamar o construtor 
		Client client = ClientBuilder.newClient();

		//WebTarget webtarget = client.target(BASE_URI);
		//WebTarget resourceWebTarget = webtarget.path(COMICS_RESOURCE);
		//WebTarget resourceWebTargetWithQueryParam = 
		//		resourceWebTarget.queryParam("ts", TS)
		//						 .queryParam("apikey", PUBLIC_KEY)
		//						 .queryParam("hash", geraHash());

		//System.out.println("Endpoint gerado com parametros: " + resourceWebTargetWithQueryParam);
		
		//Da pra devolver direto sem usar o Invocation Builder ou com o InvocationBuilder
		//Response response = resourceWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE).get();

		//Invocation.Builder invocationBuilder = resourceWebTargetWithQueryParam.request(MediaType.TEXT_PLAIN_TYPE);

		//Response response = invocationBuilder.get();
		
		//Usando no estilo de API fluente 
		Response response = client.target(BASE_URI)
						.path(COMICS_RESOURCE)
						.queryParam("ts", TS)
						.queryParam("apikey", PUBLIC_KEY)
						.queryParam("hash", geraHash())
						.request(MediaType.APPLICATION_JSON_TYPE)
						.get();
		
		System.out.println(response.getStatus());
		//Quando é dado um readEntity a conexão é fechada
		System.out.println(response.readEntity(String.class));		

	}

}
