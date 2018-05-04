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
	private ProdutoService produtoService;
		
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	
	
	public Pedido find(Integer id) {
		Optional<Pedido> obj = dao.findById(id);
		return obj.orElseThrow(()-> new ObjectNotFoundException(
				"Objeto não encontrado Id: " + id + ", Tipo: " + Pedido.class.getName()
				));
	}
	
	
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
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
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(obj);
		}
		
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		//System.out.println(obj);
		return obj;
		
		
	}

}
