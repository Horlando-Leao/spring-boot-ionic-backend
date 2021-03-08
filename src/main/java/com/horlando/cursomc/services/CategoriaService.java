package com.horlando.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.horlando.cursomc.domain.Categoria;
import com.horlando.cursomc.repositories.CategoriaRepository;
import com.horlando.cursomc.services.exceptions.DataIntegrityException;
import com.horlando.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
		 Optional<Categoria> obj = repo.findById(id);
		 return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	} 
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);//id precisa ser null para insert, != null uma entidade sera atualizada(update)
		return repo.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());//procura um objeto se não exister lanca a execessao
		return repo.save(obj);	
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);	
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
		}
		
	}

}
