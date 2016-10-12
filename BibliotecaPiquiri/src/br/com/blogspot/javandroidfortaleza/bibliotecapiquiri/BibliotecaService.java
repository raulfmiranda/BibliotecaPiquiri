package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

import java.util.ArrayList;

import br.com.blogspot.javandroidfortaleza.bibliotecapiquiri.Item.Grupo;

public class BibliotecaService {
	static ArrayList<Item> itens;
	static Integer id;
	
	public BibliotecaService() {
		itens = new ArrayList<>();
		id = 0;
	}
	
	public static ArrayList<Item> getItens() {
		return itens;
	}

	public static void setItens(ArrayList<Item> itens) {
		BibliotecaService.itens = itens;
	}

	public static Integer getId() {
		return id;
	}

	public static void setId(Integer id) {
		BibliotecaService.id = id;
	}

	public void adicionar(Item item) {
		item.setId(id++); 
		itens.add(item);
	}
	
	public void remover(Integer id) {
		int indexRemover = -1;
		for (int i = 0 ; i < itens.size() ; i++) {
			if (itens.get(i).getId() == id) {
				indexRemover = i;
				break;
			}
		}
		
		if (indexRemover != -1) {
			itens.remove(indexRemover);
		}
	}
	
	//Filtra de acordo com as características do item pesquisado
	public ArrayList<Item> filtrar(Item itemPesquisado, boolean checkAvailability) {
		ArrayList<Item> listaFiltrada = new ArrayList<Item>();
		
		int ano = itemPesquisado.getAno();
		String titulo = itemPesquisado.getTitulo();
		String autor = itemPesquisado.getAutor();
		boolean anoVazio = itemPesquisado.anoVazio();
		boolean tituloVazio = itemPesquisado.tituloVazio();
		boolean autorVazio = itemPesquisado.autorVazio();
		
		for (Item item : itens) {
			if(checkAvailability == true && item.getQuantidade().equals(0))
				continue;
			else if(itemPesquisado == null) {
				listaFiltrada.add(item);
			} else {
				if(anoVazio && tituloVazio && autorVazio) {
					listaFiltrada.add(item);
				} else if(anoVazio && tituloVazio) {
					if(item.getAutor().contains(autor))
						listaFiltrada.add(item);			
				} else if(anoVazio && autorVazio) {
					if(item.getTitulo().contains(titulo))
						listaFiltrada.add(item);
				} else if(tituloVazio && autorVazio) {
					if(item.getAno() == ano)
						listaFiltrada.add(item);
				} else {
					if(anoVazio) {
						if(item.getAutor().contains(autor) && item.getTitulo().contains(titulo))
							listaFiltrada.add(item);
					} else if(tituloVazio) {
						if(item.getAutor().contains(autor) && item.getAno() == ano)
							listaFiltrada.add(item);
					} else if(autorVazio) {
						if(item.getTitulo().contains(titulo) && item.getAno() == ano)
							listaFiltrada.add(item);
					} else {
						if(item.getTitulo().contains(titulo) && item.getAno() == ano 
								&& item.getAutor().contains(autor))
							listaFiltrada.add(item);
					}
				}
			}
		}
		
		return listaFiltrada;
	}
	
	//Cadastra 2 itens escolhidos automaticamente
	public void preCadastro(BibliotecaService service) {
		Item item1 = new Item(2015, 2, "Java for Dummies", 
				"Livro para programadores iniciantes.", "Tadeu Guimarães Junior", Grupo.LIVRO);
		Item item2 = new Item(2012, 7, "Python is cool", 
				"Revista de fãs de Python", "Robson Crusoé", Grupo.REVISTA);
		Item item3 = new Item(2015, 0, "Java is pretty cool", 
				"Livro para programadores newbies", "Tadeu Crusoé Junior", Grupo.LIVRO);
						
		service.adicionar(item1);
		service.adicionar(item2);
		service.adicionar(item3);
	}
}
