package com.raul.bibliotecapiquiri.core;

import com.raul.bibliotecapiquiri.exceptions.Log;
import com.raul.bibliotecapiquiri.service.BibliotecaService;
import com.raul.bibliotecapiquiri.view.BibliotecaView;

public class Run {

	public static void main(String[] args) {
		BibliotecaView view = new BibliotecaView();
		int opcao = -1;
		
		while (opcao != 8) {
			opcao = view.exibirMenu();
			switch (opcao) {
				case 1: {
					try {
						view.cadastrar();						
					} catch(Exception e) {
						System.out.println();
						System.out.println(e.getMessage());
					}
				} break;
				case 2: {
					//Pesquisa dentre itens disponíveis ou não
					view.pesquisar(false);
				} break;
				case 3: {
					//Pesquisa apenas itens disponíveis
					view.pesquisar(true);
				} break;
				case 4: {
					try {
						view.emprestar();						
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} break;
				case 5: {
					view.devolver();
				} break;
				case 6: {
					view.excluir();
				} break;
				case 7: {
					BibliotecaView.imprimirLista(BibliotecaService.itens);
				} break;
				case 8: {
					System.out.println();
					System.out.println(Log.info("Encerrando Biblioteca Piquiri..."));
				} break;
				default: System.out.println(Log.erro("Opção Inválida. Tente novamente."));break;
			}
		}
		System.out.println(Log.info("Sistema da Biblioteca Piquiri finalizado."));
	}
	
}
