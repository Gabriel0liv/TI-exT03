package com.example.oficina.repository;

import com.example.oficina.domain.Assistencia;
import org.springframework.data.jpa.repository.JpaRepository;

// O Spring Data gera automaticamente a implementacao CRUD.
public interface AssistenciaRepository extends JpaRepository<Assistencia, Long> {
}
