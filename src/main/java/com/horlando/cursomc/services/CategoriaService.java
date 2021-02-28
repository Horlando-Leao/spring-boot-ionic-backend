package com.horlando.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horlando.cursomc.domain.Categoria;
import com.horlando.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	//automaticamente instanciada, pelo mecanismo de injeção de dependecias
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
		} 

}
