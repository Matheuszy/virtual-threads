package org.example.produtotesteapi.service;

import jakarta.transaction.Transactional;
import org.example.produtotesteapi.dto.ClienteRequest;
import org.example.produtotesteapi.dto.ClienteResponse;
import org.example.produtotesteapi.enums.StatusCliente;
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

    public ClienteResponse buscarClientePorId(Long id) {
        var cliente = clienteRepositorie.findById(id);
        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }
        return clienteMapper.toDto(cliente.get());
    }

    @Transactional
    public ResponseEntity<ClienteResponse> cadastrarCliente(ClienteRequest request) {
        if (clienteRepositorie.existsByCpf(request.cpf())) {
            return ResponseEntity.status(409).build();
        }
        Cliente novoCliente = clienteMapper.toModel(request);
        clienteRepositorie.save(novoCliente);
        ClienteResponse response = clienteMapper.toDto(novoCliente);
        return ResponseEntity.status(201).body(response);
    }

    @Transactional
    public ResponseEntity<ClienteResponse> atualizarCliente(Long id, ClienteRequest request) {
        if (!clienteRepositorie.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }

        var clienteAtualizado = clienteRepositorie.save(clienteMapper.toModel(request));
        ClienteResponse response = clienteMapper.toDto(clienteAtualizado);
        return ResponseEntity.status(200).body(response);
    }

    @Transactional
    public ResponseEntity<ClienteResponse> desativarCliente(Long id, ClienteRequest request) {
        var cliente = clienteRepositorie.findById(id);
        if (cliente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var clienteDesativado = cliente.get();
        clienteDesativado.setStatus(StatusCliente.INATIVO);
        clienteRepositorie.save(clienteDesativado);
        ClienteResponse response = clienteMapper.toDto(clienteDesativado);
        return ResponseEntity.status(200).body(response);

    }


}
