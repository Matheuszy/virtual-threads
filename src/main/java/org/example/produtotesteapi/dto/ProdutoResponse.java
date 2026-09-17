package org.example.produtotesteapi.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        String nome,
        BigDecimal preco,
        Integer quantidade
) {
}
