package com.socialbooks.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@Entity
public class Livro {
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@JsonInclude(Include.NON_NULL)
	@NotBlank
	private String nome;
	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyy")
	private Date publicacao;
	@JsonInclude(Include.NON_NULL)
	private String editora;
	@JsonInclude(Include.NON_NULL)
	@NotNull(message="Resumo deve ser preenchido")
	@Size(max= 100, message="Resumo nao pode ter mais q 100 caracteres")
	private String resumo;
	@JsonInclude(Include.NON_EMPTY)
	@OneToMany(mappedBy= "livro")
	private List<Comentario> listComentarios;
	@ManyToOne
	@JoinColumn(name="autor_id")
	@JsonInclude(Include.NON_NULL)
	private Autor autor;
	
	public Livro(){
	}
	
	public Livro(String nome) {
		super();
		this.nome = nome;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getPublicacao() {
		return publicacao;
	}
	public void setPublicacao(Date publicacao) {
		this.publicacao = publicacao;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getResumo() {
		return resumo;
	}
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	public List<Comentario> getListComentarios() {
		return listComentarios;
	}
	public void setListComentarios(List<Comentario> listComentarios) {
		this.listComentarios = listComentarios;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
