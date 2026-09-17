package org.example.produtotesteapi.repository;

import org.example.produtotesteapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepositorie extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByNome(String nome);

    @Modifying
    @Query("""
    UPDATE Produto p
    SET p.quantidade = p.quantidade - :quantidade
    WHERE p.id = :id
      AND p.quantidade >= :quantidade
""")
    int reservar(@Param("id") Integer id, @Param("quantidade") Integer quantidade
    );
}
