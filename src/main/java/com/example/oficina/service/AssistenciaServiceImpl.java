package com.example.oficina.service;

import com.example.oficina.domain.Assistencia;
import com.example.oficina.domain.EstadoAssistencia;
import com.example.oficina.repository.AssistenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
// Camada de logica de negocio. O endpoint chama este servico e nao o repository diretamente.
public class AssistenciaServiceImpl implements AssistenciaService {

  private final AssistenciaRepository repository;

  public AssistenciaServiceImpl(AssistenciaRepository repository) {
    this.repository = repository;
  }

  @Override
  public Assistencia adicionar(String dataRealizacao, String matricula, String funcionario, Double valor) {
    Assistencia assistencia = new Assistencia();
    // Transforma os dados do pedido na entidade persistente.
    assistencia.setDataRealizacao(dataRealizacao);
    assistencia.setMatricula(matricula);
    assistencia.setFuncionario(funcionario);
    assistencia.setValor(valor);
    // Os novos registos comecam em RECEBIDO, como pede o enunciado.
    assistencia.setEstado(EstadoAssistencia.RECEBIDO);
    // save() insere na H2 e devolve a entidade gerida com o id atribuido.
    return repository.save(assistencia);
  }

  @Override
  public Optional<Assistencia> detalhe(Long idAssistencia) {
    return repository.findById(idAssistencia);
  }

  @Override
  public boolean eliminar(Long idAssistencia) {
    if (!repository.existsById(idAssistencia)) {
      return false;
    }
    // deleteById remove o registo se ele existir.
    repository.deleteById(idAssistencia);
    return true;
  }
}
