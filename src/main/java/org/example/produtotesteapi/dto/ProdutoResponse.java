package org.example.produtotesteapi.dto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Integer id,
        String nome,
        BigDecimal preco,
        Integer quantidade
) {
}
