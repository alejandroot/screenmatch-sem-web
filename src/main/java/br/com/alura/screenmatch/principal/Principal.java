package br.com.alura.screenmatch.principal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import br.com.alura.screenmatch.model.DadosSerie;
import br.com.alura.screenmatch.model.DadosTemporada;
import br.com.alura.screenmatch.service.ConsumoApi;
import br.com.alura.screenmatch.service.ConverteDados;


public class Principal {
	
	
	private Scanner leitura = new Scanner(System.in);
	
	private ConsumoApi consumoApi = new ConsumoApi();
	
	private ConverteDados conversor = new ConverteDados();
	
	private final String ENDERECO = "https://www.omdbapi.com/?t=";
	private final String API_KEY = "&apikey=722c13e0";
	
		
	public void exibeMenu() {
		
		System.out.println("Digite o nome da série para busca");
		
		var nomeSerie = leitura.nextLine();
		
		var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);
		
		DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
		
		System.out.println(dadosSerie);
		
		List<DadosTemporada> listDadosTemporadas = new ArrayList<DadosTemporada>();
		
		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
			DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
			listDadosTemporadas.add(dadosTemporada);
		}
		
		listDadosTemporadas.forEach(temp -> temp.episodios().forEach(ep -> System.out.println(ep.titulo())));
		
		
		
		
		
		
		
		
	}

}
