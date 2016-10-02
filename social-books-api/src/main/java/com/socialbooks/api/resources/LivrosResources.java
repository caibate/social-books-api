package com.socialbooks.api.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	public void salvar(@RequestBody Livro livro){
		livroRepository.save(livro);
	}
}
