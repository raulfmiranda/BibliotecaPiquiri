package com.raul.bibliotecapiquiri.view;

import java.util.ArrayList;
import java.util.Scanner;

import com.raul.bibliotecapiquiri.bean.Item;
import com.raul.bibliotecapiquiri.bean.Item.Grupo;
import com.raul.bibliotecapiquiri.exceptions.ItemIndisponivelException;
import com.raul.bibliotecapiquiri.exceptions.Log;
import com.raul.bibliotecapiquiri.exceptions.NaoPreenchidosException;
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
	
	public void cadastrar() throws NaoPreenchidosException {
		System.out.println("\n	___[ Cadastrar Item ]___");
		String ano, quantidade, tipo;
		
		do {
			System.out.print("	Ano: ");
			ano = leitor.nextLine();
			if(!service.valorNumerico(ano)) {
				System.out.println(Log.erro("Digite um valor numérico."));
			}
		} while(!service.valorNumerico(ano));
		
		do {
			System.out.print("	Quantidade: ");
			quantidade = leitor.nextLine();
			if(!service.valorNumerico(quantidade)) {
				System.out.println(Log.erro("Digite um valor numérico."));
			}
		} while(!service.valorNumerico(quantidade));
		
		System.out.print("	Título: ");
		String titulo = leitor.nextLine();
		System.out.print("	Descrição: ");
		String descricao = leitor.nextLine();
		System.out.print("	Autor: ");
		String autor = leitor.nextLine();
		
		int tipoInt = 5;
		boolean de1a5;
		do {
			de1a5 = true;
			System.out.println("	Grupos (1)Livro (2)Revista (3)Jornal (4)Artigo (5)Outros");
			System.out.print("	Escolha um dos grupos acima: ");
			tipo = leitor.nextLine();
			if(!service.valorNumerico(tipo)) {
				System.out.println(Log.erro("Digite um valor numérico [1-5]."));
			} else {
				tipoInt = Integer.parseInt(tipo);
				if(tipoInt < 1 || tipoInt > 5) {
					de1a5 = false;
					System.out.println(Log.erro("Digite um valor de 1 a 5."));
				}
			}
		} while(!service.valorNumerico(tipo) || !de1a5);
		
		Grupo grupo;
		if(tipo.equals("1"))  grupo = Grupo.LIVRO;
		else if(tipo.equals("2")) grupo = Grupo.REVISTA;
		else if(tipo.equals("3")) grupo = Grupo.JORNAL;
		else if(tipo.equals("4")) grupo = Grupo.ARTIGO;
		else grupo = Grupo.OUTROS;
		
		if(titulo.isEmpty()) {
			throw new NaoPreenchidosException(Log.erro("Item não cadastrado: ausência de título detectada."));
		} else {
			service.adicionar(new Item(Integer.parseInt(ano), Integer.parseInt(quantidade), titulo, descricao, autor, grupo));			
		}	
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
			itemPesquisado.setAno(0).setTitulo(titulo).setAutor(autor);			
		} else {
			itemPesquisado.setAno(Integer.parseInt(ano)).setTitulo(titulo).setAutor(autor);
		}
		
		imprimirLista(service.filtrar(itemPesquisado, checkAvailability));
		System.out.println(Log.info("Tecle enter para continuar..."));
		leitor.nextLine();
	}
	
	public void emprestar() throws ItemIndisponivelException {
		System.out.println("\n	___[ Solicitação de empréstimo ]___");
		System.out.print("	Entre com o ID do item: ");
		int id = leitor.nextInt(); leitor.nextLine();
		
		if(service.existeItem(id)) {
			int quantidade = BibliotecaService.itens.get(id).getQuantidade(); 
			if(quantidade > 0) {
				BibliotecaService.itens.get(id).setQuantidade(quantidade - 1);
				System.out.println(Log.info("Item emprestado com sucesso."));
			} else {
				throw new ItemIndisponivelException(Log.erro("Item indisponível."));
			}
		} else {
			System.out.println(Log.erro("Item inexistente."));
		}
	}
	
	public void devolver() {
		pesquisar(false);
		System.out.println("\n	___[ Devolução ]___");
		System.out.print("	Entre com o ID do item: ");
		int id = leitor.nextInt(); leitor.nextLine();
		
		if(service.existeItem(id)) {
			int quantidade = BibliotecaService.itens.get(id).getQuantidade(); 
			BibliotecaService.itens.get(id).setQuantidade(quantidade + 1);
			System.out.println(Log.info("Item devolvido com sucesso."));
		} else {
			System.out.println(Log.erro("Item inexistente."));
		}
	}
	
	public void excluir() {
		pesquisar(false);
		System.out.println("\n	___[ Excluir Item ]___");
		System.out.print("	Entre com o ID do item: ");
		int id = leitor.nextInt(); leitor.nextLine();
		
		if(service.existeItem(id)) {
			service.remover(id);
			System.out.println(Log.info("Item removido com sucesso."));
		} else {
			System.out.println(Log.info("Item inexistente."));
		}
	}
	
	public static void imprimirLista(ArrayList<Item> lista) {
		for (Item item : lista) {
			imprimirItem(item);
		}		
	}
	
	public static void imprimirItem(Item item) {
		System.out.println("\n	___[ Item Id("+item.getId()+") ]___");
		System.out.println("	Ano: "+item.getAno());
		System.out.println("	Quantidade: "+item.getQuantidade());
		System.out.println("	Título: "+item.getTitulo());
		System.out.println("	Descrição: "+item.getDescricao());
		System.out.println("	Grupo: "+item.getGrupo());
		System.out.println("	Autor: "+item.getAutor());
		System.out.println();
	}
}
