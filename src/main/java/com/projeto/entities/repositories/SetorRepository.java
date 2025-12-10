
package com.projeto.entities.repositories;

import com.projeto.entities.models.Setor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SetorRepository extends JpaRepository<Setor, UUID> {
    @Query("SELECT set FROM Setor set WHERE set.empresa.id = :empresa_id AND (:ativo IS NULL OR set.ativo = :ativo)")
    List<Setor> findAllByEmpresaId(@Param("empresa_id") UUID empresaId, @Param("ativo") Boolean ativo);

}
