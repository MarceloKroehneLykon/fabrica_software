package com.projeto.bases.services;

import com.projeto.bases.models.TokenTemporario;
import com.projeto.bases.repositories.TokenTemporarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TokenTemporarioService {
    private final TokenTemporarioRepository tokenTemporarioRepository;

    @Value("${token_temporario.expiration:10}")
    private int tokenExpiration;

    public void verificarCodigo(String email, String codigo) {
        tokenTemporarioRepository.findByEmailAndCodigo(email, codigo)
                .filter(t -> t.getExpirationTime().isAfter(LocalDateTime.now()))
                .ifPresentOrElse(
                        tokenTemporarioRepository::delete,
                        () -> { throw new IllegalArgumentException("Código inválido ou expirado"); }
                );
    }

    /**
     * Lê um arquivo de recurso do classpath.
     *
     * @param resourcePath Caminho do recurso relativo ao classpath
     * @return Conteúdo do arquivo como String
     */
    private String readFileFromResource(String resourcePath) {
        try {
            ClassPathResource resource = new ClassPathResource(resourcePath);
            Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException("Não foi possível ler o arquivo de template: " + resourcePath, e);
        }
    }
}
