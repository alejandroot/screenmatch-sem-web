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
		
		listDadosTemporadas.forEach(System.out::println);
		//listDadosTemporadas.forEach(dadoTemporada -> System.out.println(dadoTemporada));
		
//		for(int i = 0; i < dadosSerie.totalTemporadas(); i++) {
//			List<DadosEpisodio> listDadosEpisodio = listDadosTemporadas.get(i).episodios();
//			for(int j = 0; j < listDadosEpisodio.size(); j++) {
//				System.out.println(listDadosEpisodio.get(j).titulo());
//			}
//		}
		
		listDadosTemporadas.forEach(temp -> temp.episodios().forEach(ep -> System.out.println(ep.titulo())));
		
		List<String> listNomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");
		
		listNomes.stream()
			.sorted()
			.limit(3)
			.filter(n -> n.startsWith("N"))
			.map(n-> n.toUpperCase())
			.forEach(System.out::println);
		
		
		
		
		
		
	}

}
