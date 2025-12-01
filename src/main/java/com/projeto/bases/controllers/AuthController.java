package com.projeto.bases.controllers;

import com.projeto.bases.requests.SessaoUsuarioDTO;
import com.projeto.bases.securities.JwtUtils;
import com.projeto.bases.services.TokenTemporarioService;
import com.projeto.bases.models.Cargo;
import com.projeto.entities.models.Empresa;
import com.projeto.entities.models.Funcionario;
import com.projeto.entities.services.EmpresaService;
import com.projeto.entities.services.FuncionarioService;
import lombok.Data;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private TokenTemporarioService tokenTemporarioService;

    @Autowired
    private FuncionarioService funcionarioService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) throws Exception {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getSenha()));

        String token = jwtUtil.generateToken((UserDetails) auth.getPrincipal());

        SecurityContextHolder.getContext().setAuthentication(auth);

        Empresa empresa = empresaService.findById(empresaService.getEmpresaId());

        Funcionario funcionario = empresaService.getFuncionario();
        return ResponseEntity.ok(
            new SessaoUsuarioDTO(
                funcionario.getId(),
                token,
                funcionario.getNome(),
                (Cargo) auth.getAuthorities().stream().findFirst().orElse(null)
            )
        );
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validarToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Token inválido");
        }

        String token = authHeader.substring(7);
        String username = jwtUtil.extractUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        boolean isValid = jwtUtil.validateToken(token, userDetails);

        if (isValid) {
            return ResponseEntity.ok().body(Map.of("valid", true));
        } else {
            return ResponseEntity.status(401).body(Map.of("valid", false));
        }
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<Void> verificarCodigo(@RequestBody ValidarCodigoRequest request) {
       tokenTemporarioService.verificarCodigo(request.getEmail(), request.getCodigo());
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/alterar-senha")
    public ResponseEntity<Void> alterarSenha(@RequestBody AlterarSenhaRequest request) {
        this.funcionarioService.alterarSenha(request.getEmail(), request.getSenha());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/validar_senha")
    public ResponseEntity<?> validarSenha(@RequestBody String senha) {
        try {
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(funcionarioService.findByLogado().getEmail(), senha)
            );

            return ResponseEntity.ok(Map.of("valid", true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("valid", false));
        }
    }

}

@Data
class AuthRequest {
    private String email;
    private String senha;
}

@Value
class ValidarCodigoRequest {
    String email;
    String codigo;
}

@Value
class AlterarSenhaRequest {
    String email;
    String senha;
}

