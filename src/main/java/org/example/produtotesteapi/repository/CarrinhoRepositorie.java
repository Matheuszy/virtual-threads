package org.example.produtotesteapi.repository;

import org.example.produtotesteapi.model.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarrinhoRepositorie extends JpaRepository<Carrinho, Integer> {
}
