package com.guto1906.javacompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.Categoria;
import com.guto1906.javacompleto.repositories.CategoriaRepository;
import com.guto1906.javacompleto.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository dao;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Categoria.class.getName()
				));
	}

}
