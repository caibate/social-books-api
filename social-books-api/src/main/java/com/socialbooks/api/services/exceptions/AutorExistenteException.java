package com.socialbooks.api.services.exceptions;

public class AutorExistenteException extends RuntimeException {
	public AutorExistenteException(String mensagem){
		super(mensagem);
	}
	public AutorExistenteException(String mensagem, Throwable throwable){
		super(mensagem, throwable);
	}
}
