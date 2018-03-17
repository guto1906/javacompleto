package com.guto1906.javacompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.Categoria;
import com.guto1906.javacompleto.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository dao;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = dao.findById(id);
		return obj.orElse(null);
	}

}
