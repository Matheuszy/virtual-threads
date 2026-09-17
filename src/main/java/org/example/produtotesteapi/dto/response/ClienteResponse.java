package org.example.produtotesteapi.dto.response;

import org.example.produtotesteapi.enums.StatusCliente;
import org.example.produtotesteapi.model.valueobjetct.Endereco;

import java.time.LocalDate;

public record ClienteResponse(
        Long id,
        String nome,
        String cpf,
        String email,
        StatusCliente status,
        Endereco endereco,

        LocalDate cadastradoEm
) {
}
