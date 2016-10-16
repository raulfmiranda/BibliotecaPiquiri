package com.raul.bibliotecapiquiri.exceptions;

public class ItemIndisponivelException extends Exception {
	public ItemIndisponivelException(){ }
	
	public ItemIndisponivelException(String mensagem) {
		super(mensagem);		
	}
}
