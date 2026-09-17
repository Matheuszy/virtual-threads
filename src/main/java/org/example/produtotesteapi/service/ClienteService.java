package org.example.produtotesteapi.service;

import org.example.produtotesteapi.dto.ClienteRequest;
import org.example.produtotesteapi.dto.ClienteResponse;
import org.example.produtotesteapi.mapper.ClienteMapper;
import org.example.produtotesteapi.model.Cliente;
import org.example.produtotesteapi.repository.ClienteRepositorie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    private final ClienteRepositorie clienteRepositorie;

    private final ClienteMapper clienteMapper;

    public ClienteService(ClienteRepositorie clienteRepositorie, ClienteMapper clienteMapper) {
        this.clienteRepositorie = clienteRepositorie;
        this.clienteMapper = clienteMapper;
    }

    public ResponseEntity<ClienteResponse> cadastrarCliente(ClienteRequest request) {

        if (clienteRepositorie.existsByCpf(request.cpf())) {
            return ResponseEntity.badRequest().build();
        }
        Cliente novoCliente = clienteMapper.toModel(request);
        clienteRepositorie.save(novoCliente);
        ClienteResponse response = clienteMapper.toDto(novoCliente);
        return ResponseEntity.status(201).body(response);
    }
}
