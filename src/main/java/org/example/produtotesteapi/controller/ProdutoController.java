package org.example.produtotesteapi.controller;

import org.example.produtotesteapi.dto.ProdutoResponse;
import org.example.produtotesteapi.dto.ProdutoRequest;
import org.example.produtotesteapi.service.ProdutoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ProdutoResponse>> findById(@PathVariable Integer id) {
        return service.findById(id);
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<Optional<ProdutoResponse>> findByNome(@PathVariable String nome) {
        return service.findByNome(nome);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<ProdutoResponse> cadastrarProduto(@RequestBody ProdutoRequest request) {
        return service.createProduct(request);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable Integer id, @RequestBody ProdutoRequest request) {
        return service.updateProduct(id, request);
    }

    @PostMapping("/{id}/reservar/{quantidade}")
    public ResponseEntity<Void> reservar(
            @PathVariable Integer id, @PathVariable Integer quantidade) {
        service.reservarProduto(id, quantidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        return service.deleteProduct(id);
    }
}
