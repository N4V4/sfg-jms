package n4v.sfgjms.sender;

import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import n4v.sfgjms.config.JmsConfig;
import n4v.sfgjms.model.HelloWorldMessage;

@Component
@RequiredArgsConstructor
public class HelloSender {
	
	private final JmsTemplate jmsTemplate;
	private final ObjectMapper objectMapper;
	
	//@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		
		System.out.println("Invio un messaggio ogni 2 secondi");
		
		HelloWorldMessage message = HelloWorldMessage.builder()
				.id(UUID.randomUUID())
				.Message("Ciao Mondo ??")
				.build();
		
		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
		
		System.out.println("Messaggio inviato");
	}
	
	@Scheduled(fixedRate = 2000)
	public void sendAndReceiveMessage() {
		
		System.out.println("Invio un messaggio ogni 2 secondi");
		
		HelloWorldMessage message = HelloWorldMessage.builder()
				.id(UUID.randomUUID())
				.Message("Ciao Mondo ??")
				.build();
		
		Message messaggioRicevuto = jmsTemplate.sendAndReceive(JmsConfig.MY_SEND_RCV_QUEUE, new MessageCreator() {
			
			@Override
			public Message createMessage(Session session) throws JMSException {
				try {
					Message messaggio = session.createTextMessage(objectMapper.writeValueAsString(message));
					messaggio.setStringProperty("_type", "n4v.sfgjms.model.HelloWorldMessage");
					return messaggio;
				} catch (JsonProcessingException e) {
					throw new JMSException("Errore JMS");
				}
			}
		});
		
		System.out.println("Messaggio inviato");
	}

}
