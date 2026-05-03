package com.example.oficina.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(
      ApplicationContext applicationContext) {
    // O MessageDispatcherServlet funciona como front controller do SOAP.
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    // Faz com que o WSDL gerado use o host/porto atual.
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<>(servlet, "/ws/*");
  }

  @Bean(name = "oficina")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema oficinaSchema) {
    // Publica o contrato WSDL a partir do XSD, seguindo o estilo contract-first.
    DefaultWsdl11Definition wsdl = new DefaultWsdl11Definition();
    wsdl.setPortTypeName("OficinaPort");
    wsdl.setLocationUri("/ws");
    wsdl.setTargetNamespace("http://example.com/oficina");
    wsdl.setSchema(oficinaSchema);
    return wsdl;
  }

  @Bean
  public XsdSchema oficinaSchema() {
    // O XSD descreve as mensagens XML aceites e devolvidas pelo servico.
    return new SimpleXsdSchema(new ClassPathResource("assistencias.xsd"));
  }
}
