package com.guto1906.javacompleto.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guto1906.javacompleto.domain.ItemPedido;
import com.guto1906.javacompleto.domain.PagamentoComBoleto;
import com.guto1906.javacompleto.domain.Pedido;
import com.guto1906.javacompleto.domain.enums.EstadoPagamento;
import com.guto1906.javacompleto.repositories.ItemPedidoRepository;
import com.guto1906.javacompleto.repositories.PagamentoRepository;
import com.guto1906.javacompleto.repositories.PedidoRepository;
import com.guto1906.javacompleto.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository dao;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
			
	@Autowired
	private ProdutoService ProdutoService;
		
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()
				));
	}
	
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
				
		obj = dao.save(obj);
		pagamentoRepository.save(obj.getPagamento());		
		
		for(ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.00);
			ip.setPreco(ProdutoService.find(ip.getProduto().getId()).getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		
		return obj;
		
		
	}

}
