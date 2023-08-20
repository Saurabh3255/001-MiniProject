package in.ashokit.props;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

import java.util.HashMap;


@Data
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "plani-api")
@Configuration
public class AppProperties
{
	private Map<String ,String> messages = new HashMap<>();

	public Map<String, String> getMessages() {
		return messages;
	}

	public void setMessages(Map<String, String> messages) {
		this.messages = messages;
	}
	
}
