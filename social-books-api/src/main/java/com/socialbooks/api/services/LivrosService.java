package com.socialbooks.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.socialbooks.api.entity.Livro;
import com.socialbooks.api.repository.LivrosRepository;
import com.socialbooks.api.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	@Autowired
	private LivrosRepository livroRepository;
	public List<Livro> listarLivro(){
		return livroRepository.findAll();
	}
	public Livro buscar(Long id){
		Livro l = livroRepository.findOne(id);
		if(l == null) throw new LivroNaoEncontradoException("Livro não pode ser encontrado");
		return l;
	}
	
	public Livro salvar(Livro livro){
		livro.setId(null);
		return livroRepository.save(livro);
	}
	
	public void deletar(Long id) {
		try {
			livroRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			throw new LivroNaoEncontradoException("Livro não pode ser encontrado");
		}
	}
	
	public void atualizar(Livro l){
		verificarExistencia(l);
		livroRepository.save(l);
	}
	
	private void verificarExistencia(Livro l){
		buscar(l.getId());
	}
}
