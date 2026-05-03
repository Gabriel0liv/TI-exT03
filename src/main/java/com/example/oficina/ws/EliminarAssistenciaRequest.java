package com.example.oficina.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "eliminarAssistenciaRequest", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de pedido para a operacao de eliminar: so precisa do id.
public class EliminarAssistenciaRequest {

  // O JAXB mapeia este elemento a partir do corpo XML do SOAP.
  private Long idAssistencia;

  public Long getIdAssistencia() {
    return idAssistencia;
  }

  public void setIdAssistencia(Long idAssistencia) {
    this.idAssistencia = idAssistencia;
  }
}
