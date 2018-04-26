package com.guto1906.javacompleto.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.guto1906.javacompleto.domain.Pedido;

public abstract class AbstractMailService implements EmailService {
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setSubject("Pedido nr " + obj.getId() + " Confirmado");
		sm.setText(obj.toString());
		return sm;
	}

}
