package n4v.sfgjms.sender;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import n4v.sfgjms.config.JmsConfig;
import n4v.sfgjms.model.HelloWorldMessage;

@Component
@RequiredArgsConstructor
public class HelloSender {
	
	private final JmsTemplate jmsTemplate;
	
	@Scheduled(fixedRate = 2000)
	public void sendMessage() {
		
		System.out.println("Invio un messaggio ogni 2 secondi");
		
		HelloWorldMessage message = HelloWorldMessage.builder()
				.id(UUID.randomUUID())
				.Message("Ciao Mondo ??")
				.build();
		
		jmsTemplate.convertAndSend(JmsConfig.MY_QUEUE, message);
		
		System.out.println("Messaggio inviato");
	}

}
