package com.projeto.entities.repositories;

import com.projeto.entities.models.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, UUID> {
    @Query("SELECT emp FROM Empresa emp WHERE emp.cpfCnpj = :cpf_cnpj")
    Empresa findByCpf(@Param("cpf_cnpj") String cpfCnpj);

    @Query("SELECT fun.id FROM Funcionario fun INNER JOIN Empresa emp ON fun.empresa.id = emp.id WHERE emp.email = :email AND fun.email = :email")
    UUID findFuncionarioId(@Param("email")  String email);
}
