package com.socialbooks.api.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.socialbooks.api.entity.DetalhesErro;
import com.socialbooks.api.services.exceptions.AutorExistenteException;
import com.socialbooks.api.services.exceptions.AutorNaoEncontradoException;
import com.socialbooks.api.services.exceptions.LivroNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(LivroNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleLivroNaoEncontradoException
	(LivroNaoEncontradoException e, HttpServletRequest httpServletRequest){
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("O livro não pode ser encontrado");
		detalhesErro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> handleAutorExistenteException
	(AutorExistenteException e, HttpServletRequest httpServletRequest){
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(409l);
		detalhesErro.setTitulo("Autor existente.");
		detalhesErro.setMensagemDesenvolvedor("http://erros.socialbooks.com/409");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.CONFLICT).body(detalhesErro);
	}
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> handleAutorNaoEncontradoException
	(AutorNaoEncontradoException e, HttpServletRequest httpServletRequest){
		DetalhesErro detalhesErro = new DetalhesErro();
		detalhesErro.setStatus(404l);
		detalhesErro.setTitulo("Autor não encontrado.");
		detalhesErro.setMensagemDesenvolvedor("http://erros.socialbooks.com/404");
		detalhesErro.setTimestamp(System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(detalhesErro);
	}
}
