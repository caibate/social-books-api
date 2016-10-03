package com.socialbooks.api.services.exceptions;

public class LivroNaoEncontradoException extends RuntimeException {
	public LivroNaoEncontradoException(String mensagem){
		super(mensagem);
	}
	public LivroNaoEncontradoException(String mensagem, Throwable throwable){
		super(mensagem, throwable);
	}
}
