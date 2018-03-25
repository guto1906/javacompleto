package com.guto1906.javacompleto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.Pedido;
import com.guto1906.javacompleto.repositories.PedidoRepository;
import com.guto1906.javacompleto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository dao;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()
				));
	}

}
