package com.br.escola_api.repository;

import com.br.escola_api.model.Representante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepresentanteRepository extends JpaRepository<Representante, Long> {

    @Query("""
            FROM Representante AS r JOIN FETCH r.alunos WHERE r.email = :email
            """)
    Optional<Representante> findByEmail(String email);

    @Query("""
            FROM Representante AS r JOIN FETCH r.alunos WHERE r.id = :representanteId
            """)
    Optional<Representante> verificarId(Long representanteId);
}
