package net.multiremote.server;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * @author Gaétan
 */
@Configuration
@EnableAutoConfiguration
@ImportResource({
	"classpath:/spring-context.xml",
	"classpath:/spring-database.xml",
	"classpath:/spring-security.xml"
})
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
