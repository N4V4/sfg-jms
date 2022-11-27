package n4v.sfgjms.listener;

import javax.jms.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import n4v.sfgjms.config.JmsConfig;
import n4v.sfgjms.model.HelloWorldMessage;

@Component
public class HelloMessageListener {
	
	@JmsListener(destination = JmsConfig.MY_QUEUE)
	public void listener(@Payload HelloWorldMessage helloWorldMessage,@Headers MessageHeaders headers, Message message) {
		
		
		System.out.println("Messaggio ricevuto");
		System.out.println(helloWorldMessage);
		
		//throw new RuntimeException("Tenta di consegnare il messaggio se non riesce");
	}

}
