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

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	ComicsResource comicsResource;

	@RequestMapping("/")
	public ModelAndView index() {

		System.out.println("Entrando na home da Marvel API");
		return new ModelAndView("home");

	}

	@RequestMapping(value = "/comics", method = RequestMethod.GET)
	@Cacheable(value = "comics")
	public ModelAndView getComics() {

		ModelAndView modelAndView = new ModelAndView("listaComics");
		modelAndView.addObject("comics", comicsResource.getComics());
		return modelAndView;

	}

	@RequestMapping(value = "/comics/{id}", method = RequestMethod.GET)
	@Cacheable(value = "comicsId")
	public ModelAndView getComicsId(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("comicDetalhe");
		modelAndView.addObject("comic", comicsResource.getComicsId(id));
		return modelAndView;

	}

	@RequestMapping(value = "/comicscreator/{id}", method = RequestMethod.GET)
	@Cacheable(value = "comicsCreatorId")
	public ModelAndView getComicCreators(@PathVariable("id") Integer id) {

		ModelAndView modelAndView = new ModelAndView("listaComicCreators");
		modelAndView.addObject("comicCreators", comicsResource.getComicCreators(id));
		return modelAndView;

	}

}
