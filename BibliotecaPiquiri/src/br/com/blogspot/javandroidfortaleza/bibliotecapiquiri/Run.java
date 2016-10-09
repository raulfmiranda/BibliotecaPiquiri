package br.com.blogspot.javandroidfortaleza.bibliotecapiquiri;

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
				case 7: {
					view.listar();
				} break;
				case 8: {
					System.out.println("\n	Encerrando Biblioteca Piquiri...");
				} break;
				default: System.out.println("	Opção Inválida. Tente novamente.");break;
			}
		}
		System.out.println("\n	Obrigado e volte sempre!");
	}

}
