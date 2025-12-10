package com.projeto.cicles.repositories;

import com.projeto.cicles.models.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, UUID> {
}
