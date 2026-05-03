package com.example.oficina.domain;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "estadoAssistencia")
@XmlEnum
// Enum compativel com SOAP: o XSD expoe estes valores literais.
public enum EstadoAssistencia {
  RECEBIDO,
  EM_SERVICO,
  CONCLUIDO
}
