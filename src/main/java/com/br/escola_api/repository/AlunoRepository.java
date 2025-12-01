package com.br.escola_api.repository;

import com.br.escola_api.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("FROM Aluno a INNER JOIN FETCH a.representantes")
    List<Aluno> buscarAlunos();

    @Query("""
            FROM Aluno as a INNER JOIN FETCH a.representantes WHERE a.id = :alunoId
            """)
    Optional<Aluno> buscarAlunoRepresentanteId(Long alunoId);

}
