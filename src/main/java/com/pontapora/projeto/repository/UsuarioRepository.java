package com.pontapora.projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pontapora.projeto.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	Usuario findByLogin(String login);
}