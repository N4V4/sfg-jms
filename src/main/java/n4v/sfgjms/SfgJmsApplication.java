package n4v.sfgjms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SfgJmsApplication {

	public static void main(String[] args) throws Exception {
		
/*		ActiveMQServer server = ActiveMQServers.newActiveMQServer(new ConfigurationImpl()
				.setPersistenceEnabled(false)
				.setSecurityEnabled(false)
				.setJournalDirectory("target/data/journal")
				.addAcceptorConfiguration("invm", "vm://0"));
		
		server.start();*/

		try {
			SpringApplication.run(SfgJmsApplication.class, args);
		}catch (Throwable e) {
	        if(e.getClass().getName().contains("SilentExitException")) {
	        } else {
	        }
	    }
		
	}

}
