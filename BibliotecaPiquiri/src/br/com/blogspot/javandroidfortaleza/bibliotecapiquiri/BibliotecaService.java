package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

import java.util.ArrayList;

public class BibliotecaService {
	static ArrayList<Item> itens;
	static Integer id;
	
	public BibliotecaService() {
		itens = new ArrayList<>();
		id = 0;
	}
	
	void adicionar(Item item) {
		item.setId(id++); 
		itens.add(item);
	}
	
	void remover(Integer id) {
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
}
