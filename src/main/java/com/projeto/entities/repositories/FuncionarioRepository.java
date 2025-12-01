package com.projeto.entities.repositories;

import com.projeto.entities.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    @Query("SELECT fun FROM Funcionario fun WHERE fun.empresa.id = :empresa_id AND (:ativo IS NULL OR fun.ativo = :ativo)")
    List<Funcionario> findAllByEmpresaId(@Param("empresa_id") UUID empresaId, @Param("ativo") Boolean ativo);

    @Query("SELECT fun FROM Funcionario fun WHERE fun.email = :email")
    Funcionario findByEmail(@Param("email") String email);

    Boolean existsByEmail(String email);
}
