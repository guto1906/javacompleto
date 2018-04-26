package com.guto1906.javacompleto.services;

import org.springframework.mail.SimpleMailMessage;

import com.guto1906.javacompleto.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
