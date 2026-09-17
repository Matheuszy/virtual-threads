package org.example.produtotesteapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.produtotesteapi.model.valueobjetct.Endereco;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O e-mail é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String password,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,
        @NotNull(message = "O endereço é obrigatório")
        Endereco endereco
) {
}
