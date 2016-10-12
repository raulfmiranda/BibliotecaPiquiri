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
				case 2: {
					view.pesquisar(false);
				} break;
				case 3: {
					view.pesquisar(true);
				} break;
				case 7: {
					BibliotecaView.imprimirLista(BibliotecaService.itens);
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
