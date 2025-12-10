package com.projeto.cicles.repositories;

import com.projeto.cicles.models.Ciclo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CicloRepository extends JpaRepository<Ciclo, UUID> {

}
