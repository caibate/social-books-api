package com.socialbooks.api.resources;

import java.net.URI;
import java.util.List;

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
import com.socialbooks.api.repository.LivrosRepository;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosRepository livroRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Livro> listar(){
		return livroRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@RequestBody Livro livro){
		Livro l = livroRepository.save(livro);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(l.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Livro l= livroRepository.findOne(id);
		if(l == null) return ResponseEntity.notFound().build();
		return ResponseEntity.status(HttpStatus.OK).body(l);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public void deletar(@PathVariable("id") Long id){
		 livroRepository.delete(id);
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public void atualizar(@PathVariable("id") Long id, @RequestBody Livro livro){
		livro.setId(id);
		livroRepository.save(livro);
	}
}