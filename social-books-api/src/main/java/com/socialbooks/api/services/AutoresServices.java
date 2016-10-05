package com.socialbooks.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socialbooks.api.entity.Autor;
import com.socialbooks.api.repository.AutoresRepository;
import com.socialbooks.api.services.exceptions.AutorExistenteException;
import com.socialbooks.api.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresServices {
	@Autowired
	private AutoresRepository autoresRepository;
	
	public List<Autor> listar(){
		return autoresRepository.findAll();
	}
	
	public Autor salvar(Autor autor){
		if(autor.getId() != null){
			Autor a = autoresRepository.findOne(autor.getId());
			
			if(a!=null){
				throw new AutorExistenteException("Autor ja existe.");
			}
		}
		return autoresRepository.save(autor);
	}
	
	public Autor buscar(Long id){
		Autor autor = autoresRepository.findOne(id);
		if(autor == null){
			throw new AutorNaoEncontradoException("Autor não pode ser encontrado.");
		}
		return autor;
	}
}