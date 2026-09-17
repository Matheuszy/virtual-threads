package org.example.produtotesteapi.controller;

import jakarta.validation.Valid;
import org.example.produtotesteapi.dto.request.ClienteRequest;
import org.example.produtotesteapi.dto.response.ClienteResponse;
import org.example.produtotesteapi.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable Long id) {
        ClienteResponse clienteResponse = service.buscarClientePorId(id);
        return ResponseEntity.ok(clienteResponse);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ClienteResponse> cadastrarCliente(@RequestBody @Valid ClienteRequest request) {
        return service.cadastrarCliente(request);
    }

    @PostMapping("/atualizar/{id}")
    public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return service.atualizarCliente(id, request);
    }

    @PutMapping("/desativar/{id}")
    public ResponseEntity<ClienteResponse> desativarCliente(@PathVariable Long id, @RequestBody @Valid ClienteRequest request) {
        return service.desativarCliente(id, request);
    }

}
