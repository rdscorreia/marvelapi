package br.com.marvelapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.marvelapi.endpoint.ComicsResource;

/**
 * The Class HomeController.
 */
@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	/** The comics resource. */
	@Autowired
	ComicsResource comicsResource;

	/**
	 * Index.
	 *
	 * @return the model and view
	 */
	@RequestMapping("/")
	public ModelAndView index() {

		System.out.println("Entrando na home da Marvel API");
		return new ModelAndView("home");

	}

	/**
	 * Gets the comics.
	 *
	 * @return the comics
	 */
	@RequestMapping(value = "/comics", method = RequestMethod.GET)
	@Cacheable(value = "comics")
	public ModelAndView getComics() {

		ModelAndView modelAndView = new ModelAndView("listaComics");
		modelAndView.addObject("comics", comicsResource.getComics());
		return modelAndView;

	}

	/**
	 * Gets the comics id.
	 *
	 * @param id
	 *            the id
	 * @return the comics id
	 */
	@RequestMapping(value = "/comics/{id}", method = RequestMethod.GET)
	@Cacheable(value = "comicsId")
	public ModelAndView getComicsId(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("comicDetalhe");
		modelAndView.addObject("comic", comicsResource.getComicsId(id));
		return modelAndView;

	}

	/**
	 * Gets the comic creators.
	 *
	 * @param id
	 *            the id
	 * @return the comic creators
	 */
	@RequestMapping(value = "/comicscreator/{id}", method = RequestMethod.GET)
	@Cacheable(value = "comicsCreatorId")
	public ModelAndView getComicCreators(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("listaComicCreators");
		modelAndView.addObject("comicCreators", comicsResource.getComicCreators(id));
		return modelAndView;

	}

	/**
	 * Gets the creator id.
	 *
	 * @param resourceURI
	 *            the resource URI
	 * @return the creator id
	 */
	@RequestMapping(value = "/creatorId/{resourceURI}", method = RequestMethod.GET)
	public ModelAndView getCreatorId(@PathVariable("resourceURI") String resourceURI) {

		ModelAndView modelAndView = new ModelAndView("creatorDetalhe");
		modelAndView.addObject("creator", comicsResource.getCreatorId(resourceURI));
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/comicsNode", method = RequestMethod.GET)
	@Cacheable(value = "comicsNode")
	public ModelAndView getComicsNode() {
		
		ModelAndView modelAndView = new ModelAndView("listaComics");
		modelAndView.addObject("comics", comicsResource.getComicsNode());
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/comicsNode/{id}", method = RequestMethod.GET)
	@Cacheable(value = "comicsIdNode")
	public ModelAndView getComicsIdNode(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("comicDetalhe");
		modelAndView.addObject("comic", comicsResource.getComicsIdNode(id));
		return modelAndView;

	}

}
