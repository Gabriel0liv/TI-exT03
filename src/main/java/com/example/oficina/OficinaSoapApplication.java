package com.example.oficina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OficinaSoapApplication {

  public static void main(String[] args) {
    // O Spring Boot arranca o servidor embutido, procura os beans e carrega a configuracao JPA + SOAP.
    SpringApplication.run(OficinaSoapApplication.class, args);
  }
}
