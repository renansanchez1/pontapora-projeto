package com.pontapora.projeto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pontapora.projeto.model.Papel;
import com.pontapora.projeto.repository.PapelRepository;

@Service
public class PapelServiceImplementation implements PapelService {
	
	@Autowired
	private PapelRepository papelRepository;

	@Override
	public Papel buscarPapelPorId(Long id) {
		Optional<Papel> opt = papelRepository.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new IllegalArgumentException("Papel com id : " + id + " não existe");
		}
	}

	@Override
	public List<Papel> listarPapel() {
		List<Papel> papeis = papelRepository.findAll();
		return papeis;
	}

	@Override
	public Papel buscarPapel(String papel) {
		Papel pp = papelRepository.findByPapel(papel);
		return pp;
	}

}
