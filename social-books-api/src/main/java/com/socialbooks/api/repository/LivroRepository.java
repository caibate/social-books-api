package com.socialbooks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooks.api.entity.Livro;

public interface LivroRepository  extends JpaRepository<Livro, Long>{


}
