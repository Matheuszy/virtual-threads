package org.example.produtotesteapi.repository;

import org.example.produtotesteapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepositorie extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByNome(String nome);
}
