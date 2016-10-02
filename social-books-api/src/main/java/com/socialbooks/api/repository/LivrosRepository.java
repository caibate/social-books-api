package com.socialbooks.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socialbooks.api.entity.Livro;

public interface LivrosRepository  extends JpaRepository<Livro, Long>{


}
