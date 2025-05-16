package com.pontapora.projeto.service;

import java.util.List;

import com.pontapora.projeto.model.Papel;

public interface PapelService {
	public Papel buscarPapelPorId(Long id);
	public Papel buscarPapel(String papel);
	public List<Papel> listarPapel();
}
