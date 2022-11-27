package n4v.sfgjms.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable{
	
	static final long serialVersionUUID = -437378943493939949L;
	
	private UUID id;
	private String Message;

}
