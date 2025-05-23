package com.pontapora.projeto.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.pontapora.projeto.model.Papel;
import com.pontapora.projeto.repository.PapelRepository;

@Component
public class CarregadoraDados implements CommandLineRunner {

	@Autowired
	private PapelRepository papelRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		String[] papeis = {"ADMIN", "USER"};
		
		for (String papelString: papeis) {
			Papel papel = papelRepository.findByPapel(papelString);
			if (papel == null) {
				papel = new Papel(papelString);
				papelRepository.save(papel);
			}
		}				
	}

}