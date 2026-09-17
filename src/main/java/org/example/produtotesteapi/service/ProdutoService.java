package org.example.produtotesteapi.service;

import jakarta.transaction.Transactional;
import org.example.produtotesteapi.dto.response.ProdutoResponse;
import org.example.produtotesteapi.dto.request.ProdutoRequest;
import org.example.produtotesteapi.model.Produto;
import org.example.produtotesteapi.repository.ProdutoRepositorie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepositorie produtoRepositorie;

    public ProdutoService(ProdutoRepositorie produtoRepositorie) {
        this.produtoRepositorie = produtoRepositorie;
    }

    public Page<ProdutoResponse> findAll(Pageable pageable) {
        return produtoRepositorie.findAll(pageable)
                .map(produto ->
                        new ProdutoResponse(
                                produto.getId(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getQuantidade()));
    }

    public ResponseEntity<Optional<ProdutoResponse>> findById(Integer id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
            produtoRepositorie.findById(id)
                .map(produto ->
                        new ProdutoResponse(
                                produto.getId(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getQuantidade()))
        );
    }

    public ResponseEntity<Optional<ProdutoResponse>> findByNome(String nome) {
        if (nome == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(
            produtoRepositorie.findByNome(nome)
                .map(produto ->
                        new ProdutoResponse(
                                produto.getId(),
                                produto.getNome(),
                                produto.getPreco(),
                                produto.getQuantidade())));
    }

    @Transactional
    public ResponseEntity<ProdutoResponse> createProduct(ProdutoRequest resquest) {
        Produto newProduto = new Produto(resquest.nome(), resquest.preco(), resquest.quantidade());
        produtoRepositorie.save(newProduto);
        return ResponseEntity.status(201).body(
                new ProdutoResponse(
                        newProduto.getId(),
                        newProduto.getNome(),
                        newProduto.getPreco(),
                        newProduto.getQuantidade()));
    }

    @Transactional
    public ResponseEntity<ProdutoResponse> updateProduct(Integer id, ProdutoRequest resquest) {
        Optional<Produto> produtoOptional = produtoRepositorie.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoOptional.get();
        produto.setNome(resquest.nome());
        produto.setPreco(resquest.preco());
        produto.setQuantidade(resquest.quantidade());
        produtoRepositorie.save(produto);
        return ResponseEntity.ok(
                new ProdutoResponse(
                        produto.getId(),
                        produto.getNome(),
                        produto.getPreco(),
                        produto.getQuantidade()));
    }

    public  ResponseEntity<Void> deleteProduct(Integer id) {
        if (id == null) {
            return ResponseEntity.notFound().build();
        }
        Optional<Produto> produtoOptional = produtoRepositorie.findById(id);
        if (produtoOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        produtoRepositorie.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    public void reservarProduto(Integer id, Integer quantidade) {

        int alteracoes =
                produtoRepositorie.reservar(id, quantidade);

        if (alteracoes == 0) {
            throw new RuntimeException(
                    "Produto sem estoque"
            );
        }

        produtoRepositorie.reservar(id, quantidade);
    }

}
