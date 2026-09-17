package org.example.produtotesteapi.repository;

import org.example.produtotesteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorie extends JpaRepository<Cliente, Long> {

    boolean existsByCpf(String cpf);

}
