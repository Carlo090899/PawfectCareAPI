package PawfectCareApi.pawfectcareapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@PropertySource({"file:C:\\PawfectCareProj\\conf\\config.properties"})
public class PawfectcareapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PawfectcareapiApplication.class, args);
	}
}
