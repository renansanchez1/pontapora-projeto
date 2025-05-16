package com.pontapora.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontapora.projeto.model.Papel;

public interface PapelRepository extends JpaRepository<Papel, Long> {
	Papel findByPapel(String papel);
}