package jersey.issue5878.spring.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("issue")
public class JerseySetup extends ResourceConfig {
	public JerseySetup() {
		packages("jersey.issue5878.rest");
	}
}
