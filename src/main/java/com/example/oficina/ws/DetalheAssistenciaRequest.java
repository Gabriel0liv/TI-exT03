package com.example.oficina.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "detalheAssistenciaRequest", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de pedido simples com apenas o identificador necessario para localizar o registo.
public class DetalheAssistenciaRequest {

  // O cliente SOAP envia o id pedido no enunciado.
  private Long idAssistencia;

  public Long getIdAssistencia() {
    return idAssistencia;
  }

  public void setIdAssistencia(Long idAssistencia) {
    this.idAssistencia = idAssistencia;
  }
}
