package com.socialbooks.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socialbooks.api.entity.Autor;
@Repository
public interface AutoresRepository extends JpaRepository<Autor, Long> {
	
}
