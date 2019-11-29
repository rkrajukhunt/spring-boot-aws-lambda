package ai.jobiak.lambda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gargoylesoftware.htmlunit.WebClient;

@SpringBootApplication
// @ComponentScan(basePackages = "com.gargoylesoftware.htmlunit")
public class JobiakAwsLambdaApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobiakAwsLambdaApplication.class, args);
	}
	
	@Bean
    public WebClient webClient() {
        return new WebClient();
    }
}
