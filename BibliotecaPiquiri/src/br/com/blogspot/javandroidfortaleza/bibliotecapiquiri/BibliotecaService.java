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
