package br.com.marvelapi.controller;

public class Itera {

	public static void main(String[] args) {

		String link = "http://gateway.marvel.com/v1/public/creators/362";
		String[] linkSplit = link.split("/");
		
		System.out.println(linkSplit[linkSplit.length -1]);

	}

}
