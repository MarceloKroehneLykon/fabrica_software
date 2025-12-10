package com.projeto.cicles.repositories;

import com.projeto.cicles.models.Relatorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RelatorioRepository extends JpaRepository<Relatorio, UUID> {

    @Query("SELECT rel FROM Relatorio rel INNER JOIN Setor set ON rel.setor.id = set.id WHERE rel.empresa.id = :empresa_id")
    List<Relatorio> findAllByEmpresaId(@Param("empresa_id") UUID empresaId);
}
