package com.projeto.bases.repositories;

import com.projeto.bases.models.TokenTemporario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TokenTemporarioRepository extends JpaRepository<TokenTemporario, UUID> {
    Optional<TokenTemporario> findByEmail(String email);

    Optional<TokenTemporario> findByEmailAndCodigo(String email, String token);
}
