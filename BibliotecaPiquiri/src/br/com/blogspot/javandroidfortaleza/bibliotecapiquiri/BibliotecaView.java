package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

import java.util.ArrayList;
import java.util.Scanner;

import br.com.blogspot.javandroidfortaleza.bibliotecapiquiri.Item.Grupo;

public class BibliotecaView {
	Scanner leitor;
	BibliotecaService service;
	
	public BibliotecaView() {
		leitor = new Scanner(System.in);
		service = new BibliotecaService();
		
		//cadastra 2 itens automaticamente
		service.preCadastro(service);
	}
	
	public int exibirMenu() {
		System.out.println("\n	[ BIBLIOTECA P�BLICA DE PIQUIRI ]");
		System.out.println("	TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		System.out.println("	| 1) Cadastrar Item             |");
		System.out.println("	| 2) Pesquisar Item             |");
		System.out.println("	| 3) Pesquisar Itens dispon�veis|");
		System.out.println("	| 4) Solicita��o de empr�stimo	|");
		System.out.println("	| 5) Devolu��o                  |");
		System.out.println("	| 6) Excluir Item               |");
		System.out.println("	| 7) Listar todos os Itens      |");
		System.out.println("	| 8) Sair                       |");
		System.out.println("	|_______________________________|");
		System.out.print("	| Digite uma op��o: ");
		int opcao = leitor.nextInt(); leitor.nextLine();
		return opcao;
	}
	
	public void cadastrar() {
		System.out.println("\n	___[ Cadastrar Item ]___");
		System.out.print("	Ano: ");
		Integer ano = leitor.nextInt(); leitor.nextLine();
		System.out.print("	Quantidade: ");
		Integer quantidade = leitor.nextInt(); leitor.nextLine();
		System.out.print("	T�tulo: ");
		String titulo = leitor.nextLine();
		System.out.print("	Descri��o: ");
		String descricao = leitor.nextLine();
		System.out.print("	Autor: ");
		String autor = leitor.nextLine();
		System.out.println("	Grupos (1)Livro (2)Revista (3)Jornal (4)Artigo (5)Outros");
		System.out.print("	Escolha um dos grupos acima: ");
		int tipo = leitor.nextInt(); leitor.nextLine();
		
		Item item = new Item();
		item.setAno(ano);
		item.setQuantidade(quantidade);
		item.setTitulo(titulo);
		item.setDescricao(descricao);
		
		if(tipo == 1) item.setGrupo(Grupo.LIVRO);
		else if(tipo == 2) item.setGrupo(Grupo.REVISTA);
		else if(tipo == 3) item.setGrupo(Grupo.JORNAL);
		else if(tipo == 4) item.setGrupo(Grupo.ARTIGO);
		else item.setGrupo(Grupo.OUTROS);
		
		item.setAutor(autor);
		
		service.adicionar(item);
		
	}
	
	public void pesquisar(boolean checkAvailability) {
		System.out.println("\n	___[ Pesquisar Item ]___");
		System.out.println("	Preencha o filtro de pesquisa abaixo.");
		System.out.println("	Tecle enter caso n�o queira preencher o campo.");
		System.out.print("	Ano: ");
		String ano = leitor.nextLine();
		System.out.print("	T�tulo: ");
		String titulo = leitor.nextLine();
		System.out.print("	Autor: ");
		String autor = leitor.nextLine();
		
		Item item = new Item();
		
		if(ano.isEmpty()) {
			item.setAno(0);			
		} else {
			item.setAno(Integer.parseInt(ano));
		}
		
		item.setTitulo(titulo);
		item.setAutor(autor);
		
		listar(item, checkAvailability);
	}
	
	public void listar(Item itemPesquisado, boolean checkAvailability) {
		ArrayList<Item> itens = BibliotecaService.itens;
		
		for (Item item : itens) {
			if(checkAvailability == true && item.getQuantidade() == 0)
				continue;
			else if(itemPesquisado == null) {
				imprimirItem(item);
			} else {
				int ano = itemPesquisado.getAno();
				String titulo = itemPesquisado.getTitulo();
				String autor = itemPesquisado.getAutor();
			
				if(ano == 0 && titulo.isEmpty() && autor.isEmpty()) {
					imprimirItem(item);
				} else if(ano == 0 && titulo.isEmpty()) {
					if(item.getAutor().contains(autor))
						imprimirItem(item);			
				} else if(ano == 0 && autor.isEmpty()) {
					if(item.getTitulo().contains(titulo))
						imprimirItem(item);
				} else if(titulo.isEmpty() && autor.isEmpty()) {
					if(item.getAno() == ano)
						imprimirItem(item);
				} else {
					if(ano == 0) {
						if(item.getAutor().contains(autor) && item.getTitulo().contains(titulo))
							imprimirItem(item);
					} else if(titulo.isEmpty()) {
						if(item.getAutor().contains(autor) && item.getAno() == ano)
							imprimirItem(item);
					} else if(autor.isEmpty()) {
						if(item.getTitulo().contains(titulo) && item.getAno() == ano)
							imprimirItem(item);
					} else {
						if(item.getTitulo().contains(titulo) && item.getAno() == ano 
								&& item.getAutor().contains(autor))
							imprimirItem(item);
					}
				}
			}
		}
	}
	
	public void imprimirItem(Item item) {
		System.out.println("\n	___[ Item "+item.getId()+" ]___");
		System.out.println("	Ano: "+item.getAno());
		System.out.println("	Quantidade: "+item.getQuantidade());
		System.out.println("	T�tulo: "+item.getTitulo());
		System.out.println("	Descri��o: "+item.getDescricao());
		System.out.println("	Grupo: "+item.getGrupo());
		System.out.println("	Autor: "+item.getAutor());
		System.out.println();
	}
}
