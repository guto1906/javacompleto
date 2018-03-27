package com.guto1906.javacompleto.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.Categoria;
import com.guto1906.javacompleto.repositories.CategoriaRepository;
import com.guto1906.javacompleto.services.exceptions.DataIntegrityException;
import com.guto1906.javacompleto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository dao;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Categoria.class.getName()
				));
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return dao.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return dao.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		dao.deleteById(id);
		} catch(DataIntegrityViolationException e){
			throw new DataIntegrityException("Não é possível excluir uma categoria com produtos associados");
		}
	}
	
	public List<Categoria> findAll(){
		return dao.findAll();
	}

}
