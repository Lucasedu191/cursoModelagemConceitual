package silva.lucas.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import silva.lucas.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
