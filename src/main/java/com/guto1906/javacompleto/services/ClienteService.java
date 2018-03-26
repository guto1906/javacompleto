package com.guto1906.javacompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.Cliente;
import com.guto1906.javacompleto.repositories.ClienteRepository;
import com.guto1906.javacompleto.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository dao;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Cliente.class.getName()
				));
	}

}
