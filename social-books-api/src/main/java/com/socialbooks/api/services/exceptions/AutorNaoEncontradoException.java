package com.socialbooks.api.services.exceptions;

public class AutorNaoEncontradoException extends RuntimeException {
	public AutorNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	public AutorNaoEncontradoException(String mensagem, Throwable throwable){
		super(mensagem, throwable);
	}
}
