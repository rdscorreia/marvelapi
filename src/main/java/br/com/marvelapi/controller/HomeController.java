package br.com.marvelapi.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.marvelapi.endpoint.ComicsResource;

@RestController
@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class HomeController {

	@Autowired
	ComicsResource comicsResource;

	@RequestMapping("/")
	public String index() {
		System.out.println("Entrando na home da Marvel API");
		return "ok";

	}

	@RequestMapping(value = "/comics", method = RequestMethod.GET)
	@ResponseBody
	@Cacheable(value = "comics")
	public JsonNode getComics() throws NoSuchAlgorithmException, JsonProcessingException, IOException {

		return comicsResource.getComics();

	}

	@RequestMapping(value = "/comics/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Cacheable(value = "comicsId")
	public ModelAndView getComicsId(@PathVariable("id") Integer id) throws Exception {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(comicsResource.getComicsId(id));
		return modelAndView;

	}

	@RequestMapping(value = "/comicscreator/{id}", method = RequestMethod.GET)
	@ResponseBody
	@Cacheable(value = "comicsCreatorId")
	public ModelAndView getComicCreators(@PathVariable("id") Integer id) throws Exception {

		ModelAndView modelAndView = new ModelAndView("listaComicCreators");
		modelAndView.addObject("comicCreators", comicsResource.getComicCreators(id));
		return modelAndView;

	}

}
