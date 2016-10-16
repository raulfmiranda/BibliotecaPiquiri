package com.raul.bibliotecapiquiri.exceptions;

public class Log {

	private static enum Tipo {
		INFO, ERRO;
	}
	
	public static String info(String mensagem) {
		return rotularMensagem(Tipo.INFO, mensagem);
	}
	
	public static String erro(String mensagem) {
		return rotularMensagem(Tipo.ERRO, mensagem);
	}
	
	private static String rotularMensagem(Tipo tipo, String mensagem) {
		String mensagemRotulada = "	["+tipo.name()+"]: "+mensagem;
		return mensagemRotulada;
	}
	
}
