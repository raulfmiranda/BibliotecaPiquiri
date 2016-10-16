package com.raul.bibliotecapiquiri.exceptions;

public class NaoPreenchidosException extends Exception {
	public NaoPreenchidosException(){ }
	
	public NaoPreenchidosException(String mensagem) {
		super(mensagem);		
	}
}