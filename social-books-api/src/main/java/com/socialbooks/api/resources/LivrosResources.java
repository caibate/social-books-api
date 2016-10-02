package com.socialbooks.api.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.socialbooks.api.entity.Livro;

@RestController
public class LivrosResources {
	
	@RequestMapping(value = "/livros", method = RequestMethod.GET)
	public List<Livro> listar(){
		Livro livro1 = new Livro("REST Aplicado");
		Livro livro2 = new Livro("GIT passo-a-passo");
		Livro[] livros = {livro1, livro2};
		return Arrays.asList(livros);
	}
}
