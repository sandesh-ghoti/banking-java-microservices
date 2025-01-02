package org.eazybytes.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info =
        @Info(
            title = "Account spring-boot microservice with swagger REST Api defination",
            description = "Here is beutiful description",
            version = "v1",
            contact = @Contact(name = "Sandesh Ghoti", email = "sandesh.ghoti@gmail.com")))
public class AccountsMicroservicesApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsMicroservicesApplication.class, args);
  }
}
