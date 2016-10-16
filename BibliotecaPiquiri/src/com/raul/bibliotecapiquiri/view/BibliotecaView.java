package com.raul.bibliotecapiquiri.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.raul.bibliotecapiquiri.bean.Item;
import com.raul.bibliotecapiquiri.bean.Item.Grupo;
import com.raul.bibliotecapiquiri.exceptions.ItemIndisponivelException;
import com.raul.bibliotecapiquiri.service.BibliotecaService;

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
		
		Grupo grupo;
		if(tipo == 1)  grupo = Grupo.LIVRO;
		else if(tipo == 2) grupo = Grupo.REVISTA;
		else if(tipo == 3) grupo = Grupo.JORNAL;
		else if(tipo == 4) grupo = Grupo.ARTIGO;
		else grupo = Grupo.OUTROS;
				
		service.adicionar(new Item(ano, quantidade, titulo, descricao, autor, grupo));
	}
	
	// checkAvailability: quantidade > 0 ?
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
		
		Item itemPesquisado = new Item();
		
		if(ano.isEmpty()) {
			itemPesquisado.setAno(0).setTitulo(titulo).setAutor(autor);			
		} else {
			itemPesquisado.setAno(Integer.parseInt(ano)).setTitulo(titulo).setAutor(autor);
		}
		
		imprimirLista(service.filtrar(itemPesquisado, checkAvailability));
	}
	
	public void emprestar() throws ItemIndisponivelException {
		System.out.println("\n	___[ Solicita��o de empr�stimo ]___");
		System.out.print("	Entre com o ID do item: ");
		int id = leitor.nextInt(); leitor.nextLine();
		
		if(service.existeItem(id)) {
			int quantidade = BibliotecaService.itens.get(id).getQuantidade(); 
			if(quantidade > 0) {
				BibliotecaService.itens.get(id).setQuantidade(quantidade - 1);
				System.out.println("	Item emprestado com sucesso.");
			} else {
				//System.out.println("	Item indispon�vel.");
				throw new ItemIndisponivelException("	Item indispon�vel.");
			}
		} else {
			System.out.println("	Item inexistente.");
		}
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
		System.out.println("	T�tulo: "+item.getTitulo());
		System.out.println("	Descri��o: "+item.getDescricao());
		System.out.println("	Grupo: "+item.getGrupo());
		System.out.println("	Autor: "+item.getAutor());
		System.out.println();
	}
}
