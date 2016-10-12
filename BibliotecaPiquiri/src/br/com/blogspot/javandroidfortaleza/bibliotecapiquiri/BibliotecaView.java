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
		System.out.println("\n	[ BIBLIOTECA PÚBLICA DE PIQUIRI ]");
		System.out.println("	TTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT");
		System.out.println("	| 1) Cadastrar Item             |");
		System.out.println("	| 2) Pesquisar Item             |");
		System.out.println("	| 3) Pesquisar Itens disponíveis|");
		System.out.println("	| 4) Solicitação de empréstimo	|");
		System.out.println("	| 5) Devolução                  |");
		System.out.println("	| 6) Excluir Item               |");
		System.out.println("	| 7) Listar todos os Itens      |");
		System.out.println("	| 8) Sair                       |");
		System.out.println("	|_______________________________|");
		System.out.print("	| Digite uma opção: ");
		int opcao = leitor.nextInt(); leitor.nextLine();
		return opcao;
	}
	
	public void cadastrar() {
		System.out.println("\n	___[ Cadastrar Item ]___");
		System.out.print("	Ano: ");
		Integer ano = leitor.nextInt(); leitor.nextLine();
		System.out.print("	Quantidade: ");
		Integer quantidade = leitor.nextInt(); leitor.nextLine();
		System.out.print("	Título: ");
		String titulo = leitor.nextLine();
		System.out.print("	Descrição: ");
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
	
	// checkAvailability: quantidade > 0 ?
	public void pesquisar(boolean checkAvailability) {
		System.out.println("\n	___[ Pesquisar Item ]___");
		System.out.println("	Preencha o filtro de pesquisa abaixo.");
		System.out.println("	Tecle enter caso não queira preencher o campo.");
		System.out.print("	Ano: ");
		String ano = leitor.nextLine();
		System.out.print("	Título: ");
		String titulo = leitor.nextLine();
		System.out.print("	Autor: ");
		String autor = leitor.nextLine();
		
		Item itemPesquisado = new Item();
		
		if(ano.isEmpty()) {
			itemPesquisado.setAno(0);			
		} else {
			itemPesquisado.setAno(Integer.parseInt(ano));
		}
		
		itemPesquisado.setTitulo(titulo);
		itemPesquisado.setAutor(autor);
		
		imprimirLista(service.filtrar(itemPesquisado, checkAvailability));
	}
	
	public static void imprimirLista(ArrayList<Item> lista) {
		for (Item item : lista) {
			imprimirItem(item);
		}		
	}
	
	public static void imprimirItem(Item item) {
		System.out.println("\n	___[ Item "+item.getId()+" ]___");
		System.out.println("	Ano: "+item.getAno());
		System.out.println("	Quantidade: "+item.getQuantidade());
		System.out.println("	Título: "+item.getTitulo());
		System.out.println("	Descrição: "+item.getDescricao());
		System.out.println("	Grupo: "+item.getGrupo());
		System.out.println("	Autor: "+item.getAutor());
		System.out.println();
	}
}
