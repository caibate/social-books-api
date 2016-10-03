package com.socialbooks.api.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialbooks.api.entity.Livro;
import com.socialbooks.api.services.LivrosService;
import com.socialbooks.api.services.exceptions.LivroNaoEncontradoException;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livroService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listarLivro());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Livro livro){
		Livro l = livroService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(l.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Livro l = null;
		try{
			l= livroService.buscar(id);
		}catch(LivroNaoEncontradoException ex){
		 return ResponseEntity.notFound().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<?> deletar(@PathVariable("id") Long id){
		try{ 
			livroService.deletar(id);
		}catch(LivroNaoEncontradoException e){
			return ResponseEntity.notFound().build();
		}
		 return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<?> atualizar(@PathVariable("id") Long id, @RequestBody Livro livro){
		livro.setId(id);
		try {
			livroService.atualizar(livro);
		} catch (LivroNaoEncontradoException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.noContent().build();
	}
}