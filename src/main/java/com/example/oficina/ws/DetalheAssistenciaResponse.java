package com.example.oficina.ws;

import com.example.oficina.domain.Assistencia;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "detalheAssistenciaResponse", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de resposta que devolve a assistencia ou indica que nao foi encontrada.
public class DetalheAssistenciaResponse {

  // Mantem o estilo SUCESSO/INSUCESSO usado no exame.
  private String resultado;
  // So aparece quando o id existe na base H2.
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
