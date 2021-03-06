
package com.socialbooks.api.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.socialbooks.api.entity.Comentario;
import com.socialbooks.api.entity.Livro;
import com.socialbooks.api.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livroService;
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listar(){
		return ResponseEntity.status(HttpStatus.OK).body(livroService.listarLivro());
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<?> salvar(@Valid @RequestBody Livro livro){
		Livro l = livroService.salvar(livro);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().path("/{id}").
				buildAndExpand(l.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

//	@Cacheable(value="livros")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id){
		Livro l =  livroService.buscar(id);
//		return ResponseEntity.status(HttpStatus.OK).body(l);
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(l);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id){
		livroService.deletar(id);
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @Valid @RequestBody Livro livro){
		livro.setId(id);
		livroService.atualizar(livro);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.POST)
	public ResponseEntity<Void> adicionarComentario(@PathVariable("id") Long livroId,
									@RequestBody @Valid Comentario comentario){
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		comentario.setUsuario(auth.getName());
		
		livroService.salvarComentario(livroId, comentario);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest().
				build().toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method=RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentario(@PathVariable("id") Long livroId){
		 List<Comentario> listarComentario=livroService.listarComentario(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(listarComentario);
	}
}