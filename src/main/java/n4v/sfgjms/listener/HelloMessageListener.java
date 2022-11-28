package n4v.sfgjms.listener;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;

import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import n4v.sfgjms.config.JmsConfig;
import n4v.sfgjms.model.HelloWorldMessage;

@Component
@RequiredArgsConstructor
public class HelloMessageListener {
	
	private final JmsTemplate JmsTemplate;
	
	@JmsListener(destination = JmsConfig.MY_QUEUE)
	public void listener(@Payload HelloWorldMessage helloWorldMessage,@Headers MessageHeaders headers, Message message) {
		
		
		System.out.println("Messaggio ricevuto");
		System.out.println(helloWorldMessage);
		
		//throw new RuntimeException("Tenta di consegnare il messaggio se non riesce");
	}
	
	@JmsListener(destination = JmsConfig.MY_SEND_RCV_QUEUE)
	public void listenerForHello(@Payload HelloWorldMessage helloWorldMessage,@Headers MessageHeaders headers, Message message) throws JmsException, JMSException {
		
		HelloWorldMessage payLoadMsg = HelloWorldMessage.builder()
				.id(UUID.randomUUID())
				.Message("Ciao Mondo con risposta ??")
				.build();
		
		JmsTemplate.convertAndSend(message.getJMSReplyTo(),payLoadMsg);

		//throw new RuntimeException("Tenta di consegnare il messaggio se non riesce");
	}

}
