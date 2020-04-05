package br.com.accountcard.authorizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(value = {"br.com.accountcard.authorizer", "br.com.accountcard.domain"})
public class AuthorizerApplication {

	public static void main(final String[] args) {
		SpringApplication.run(AuthorizerApplication.class, args);
	}

}
