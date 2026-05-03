package com.example.oficina.ws;

import com.example.oficina.domain.Assistencia;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "adicionarAssistenciaResponse", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de resposta SOAP: o JAXB converte este objeto novamente para XML.
public class AdicionarAssistenciaResponse {

  // A string de resultado no estilo do exame facilita a leitura no SOAPUI.
  private String resultado;
  // Devolver a entidade completa permite ver o id gerado e o estado.
  private Assistencia assistencia;

  public String getResultado() {
    return resultado;
  }

  public void setResultado(String resultado) {
    this.resultado = resultado;
  }

  public Assistencia getAssistencia() {
    return assistencia;
  }

  public void setAssistencia(Assistencia assistencia) {
    this.assistencia = assistencia;
  }
}
