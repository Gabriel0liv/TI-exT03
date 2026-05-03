package com.example.oficina.service;

import com.example.oficina.domain.Assistencia;

import java.util.Optional;

// Camada de servico: separa as regras de negocio do endpoint SOAP.
public interface AssistenciaService {

  // Cria uma nova assistencia com o estado inicial RECEBIDO.
  Assistencia adicionar(String dataRealizacao, String matricula, String funcionario, Double valor);

  // Vai buscar um registo pelo id.
  Optional<Assistencia> detalhe(Long idAssistencia);

  // Remove um registo pelo id.
  boolean eliminar(Long idAssistencia);
}
