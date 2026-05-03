package com.example.oficina.ws;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "adicionarAssistenciaRequest", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Bean de pedido SOAP: o JAXB usa esta classe para ler o XML enviado pelo cliente.
public class AdicionarAssistenciaRequest {

  // Estes campos correspondem aos elementos definidos no XSD.
  private String dataRealizacao;
  private String matricula;
  private String funcionario;
  private Double valor;

  public String getDataRealizacao() {
    return dataRealizacao;
  }

  public void setDataRealizacao(String dataRealizacao) {
    this.dataRealizacao = dataRealizacao;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getFuncionario() {
    return funcionario;
  }

  public void setFuncionario(String funcionario) {
    this.funcionario = funcionario;
  }

  public Double getValor() {
    return valor;
  }

  public void setValor(Double valor) {
    this.valor = valor;
  }
}
