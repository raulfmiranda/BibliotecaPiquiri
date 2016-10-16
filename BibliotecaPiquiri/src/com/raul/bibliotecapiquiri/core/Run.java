package com.raul.bibliotecapiquiri.core;

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
					view.cadastrar();
				} break;
				case 2: {
					//Pesquisa dentre itens dispon�veis ou n�o
					view.pesquisar(false);
				} break;
				case 3: {
					//Pesquisa apenas itens dispon�veis
					view.pesquisar(true);
				} break;
				case 4: {
					try {
						view.emprestar();						
					} catch(Exception e) {
						System.out.println(e.getMessage());
					}
				} break;
				case 7: {
					BibliotecaView.imprimirLista(BibliotecaService.itens);
				} break;
				case 8: {
					System.out.println("\n	Encerrando Biblioteca Piquiri...");
				} break;
				default: System.out.println("	Op��o Inv�lida. Tente novamente.");break;
			}
		}
		System.out.println("\n	Obrigado e volte sempre!");
	}

}
