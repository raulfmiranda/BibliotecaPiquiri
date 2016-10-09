package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

import java.util.ArrayList;
import java.util.Scanner;

public class BibliotecaView {
	Scanner leitor;
	BibliotecaService service;
	
	public BibliotecaView() {
		leitor = new Scanner(System.in);
		service = new BibliotecaService();
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
		
		Item item = new Item();
		item.setAno(ano);
		item.setQuantidade(quantidade);
		item.setTitulo(titulo);
		item.setDescricao(descricao);
		item.setAutor(autor);
		
		service.adicionar(item);
		
	}
	
	public void listar() {
		
		ArrayList<Item> itens = BibliotecaService.itens;
		
		for (Item item : itens) {
			System.out.println("\n	___[ Item "+item.getId()+" ]___");
			System.out.println("	Ano: "+item.getAno());
			System.out.println("	Quantidade: "+item.getQuantidade());
			System.out.println("	T�tulo: "+item.getTitulo());
			System.out.println("	Descri��o: "+item.getDescricao());
			System.out.println("	Autor: "+item.getAutor());
			System.out.println();
		}
	}
}
