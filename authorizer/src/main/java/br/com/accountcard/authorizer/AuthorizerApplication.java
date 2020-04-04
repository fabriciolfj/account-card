package br.com.accountcard.authorizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AuthorizerApplication.class, args);
	}

}
