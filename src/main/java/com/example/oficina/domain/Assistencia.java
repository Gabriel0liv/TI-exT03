package com.example.oficina.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "assistencias")
@XmlRootElement(name = "assistencia", namespace = "http://example.com/oficina")
@XmlAccessorType(XmlAccessType.FIELD)
// Esta classe e ao mesmo tempo uma entidade JPA e um modelo XML para SOAP.
public class Assistencia {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  // Chave primaria gerada automaticamente, como pede o enunciado.
  @XmlElement
  private Long idAssistencia;

  @Column(nullable = false)
  // Data guardada como String para simplificar o modelo no contexto do exame.
  @XmlElement
  private String dataRealizacao;

  @Column(nullable = false)
  // Matricula / identificador do veiculo.
  @XmlElement
  private String matricula;

  @Column(nullable = false)
  // Funcionario que realizou o trabalho.
  @XmlElement
  private String funcionario;

  @Column(nullable = false)
  // Valor do servico.
  @XmlElement
  private Double valor;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  // Guardado como texto na base e nao como numero ordinal.
  @XmlElement
  private EstadoAssistencia estado;

  public Assistencia() {
    // JPA e JAXB precisam de um construtor vazio.
  }

  public Long getIdAssistencia() {
    return idAssistencia;
  }

  public void setIdAssistencia(Long idAssistencia) {
    this.idAssistencia = idAssistencia;
  }

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

  public EstadoAssistencia getEstado() {
    return estado;
  }

  public void setEstado(EstadoAssistencia estado) {
    this.estado = estado;
  }
}
