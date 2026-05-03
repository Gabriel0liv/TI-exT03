package com.example.oficina.ws;

import com.example.oficina.domain.Assistencia;
import com.example.oficina.service.AssistenciaService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
// Endpoint SOAP: esta classe recebe pedidos XML e devolve respostas XML.
public class AssistenciaEndpoint {

  private static final String NAMESPACE = "http://example.com/oficina";
  private final AssistenciaService assistenciaService;

  public AssistenciaEndpoint(AssistenciaService assistenciaService) {
    this.assistenciaService = assistenciaService;
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "adicionarAssistenciaRequest")
  @ResponsePayload
  public AdicionarAssistenciaResponse adicionar(@RequestPayload AdicionarAssistenciaRequest request) {
    AdicionarAssistenciaResponse response = new AdicionarAssistenciaResponse();
    // Chama a camada de servico e depois embrulha o resultado numa resposta SOAP.
    Assistencia assistencia = assistenciaService.adicionar(
        request.getDataRealizacao(),
        request.getMatricula(),
        request.getFuncionario(),
        request.getValor());
    response.setResultado("SUCESSO");
    response.setAssistencia(assistencia);
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "detalheAssistenciaRequest")
  @ResponsePayload
  public DetalheAssistenciaResponse detalhe(@RequestPayload DetalheAssistenciaRequest request) {
    DetalheAssistenciaResponse response = new DetalheAssistenciaResponse();
    // Optional permite distinguir de forma clara entre "encontrado" e "nao encontrado".
    assistenciaService.detalhe(request.getIdAssistencia())
        .ifPresentOrElse(
            assistencia -> {
              response.setResultado("SUCESSO");
              response.setAssistencia(assistencia);
            },
            () -> response.setResultado("INSUCESSO"));
    return response;
  }

  @PayloadRoot(namespace = NAMESPACE, localPart = "eliminarAssistenciaRequest")
  @ResponsePayload
  public EliminarAssistenciaResponse eliminar(@RequestPayload EliminarAssistenciaRequest request) {
    EliminarAssistenciaResponse response = new EliminarAssistenciaResponse();
    // Converte o boolean do servico no estilo SUCESSO/INSUCESSO do enunciado.
    response.setResultado(assistenciaService.eliminar(request.getIdAssistencia()) ? "SUCESSO" : "INSUCESSO");
    return response;
  }
}
