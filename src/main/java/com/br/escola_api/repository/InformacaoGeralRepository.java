package com.br.escola_api.repository;

import com.br.escola_api.model.InformacaoGeral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface InformacaoGeralRepository extends JpaRepository<InformacaoGeral, Long> {

    @Query("""
                FROM InformacaoGeral AS ig JOIN FETCH aluno AS a
            """)
    List<InformacaoGeral> findAll();

    @Query("""
        FROM InformacaoGeral AS ig JOIN FETCH aluno AS a WHERE ig.id = :informacaoGeralId
    """)
    Optional<InformacaoGeral> findById(Long informacaoGeralId);

    @Query("""
        SELECT ig FROM InformacaoGeral ig JOIN FETCH ig.aluno a WHERE a.id = :alunoId
        AND FUNCTION('date', ig.dataCadastro) = :dataCadastro
    """)
    List<InformacaoGeral> listarInformacoesPorAlunoIdDataCadastro(
            @Param("alunoId") Long alunoId,
            @Param("dataCadastro") LocalDate dataCadastro
    );

    @Query(""" 
        SELECT ig FROM InformacaoGeral ig JOIN FETCH ig.aluno a WHERE a.id = :alunoId
    """)
    List<InformacaoGeral> listarInformacoesPorAlunoId(
            @Param("alunoId") Long alunoId
    );


}
