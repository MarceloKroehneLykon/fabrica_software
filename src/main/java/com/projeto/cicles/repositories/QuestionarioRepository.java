package com.projeto.cicles.repositories;

import com.projeto.cicles.models.Questionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionarioRepository extends JpaRepository<Questionario, UUID> {
}
