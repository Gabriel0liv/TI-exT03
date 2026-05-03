package com.example.oficina.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "eliminarAssistenciaResponse", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de resposta simples que diz ao cliente se a eliminacao funcionou.
public class EliminarAssistenciaResponse {

  // SUCESSO se foi eliminado, INSUCESSO se o id nao existir.
  private String resultado;

  public String getResultado() {
    return resultado;
  }

  public void setResultado(String resultado) {
    this.resultado = resultado;
  }
}
