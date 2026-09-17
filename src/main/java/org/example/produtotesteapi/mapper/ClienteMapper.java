package org.example.produtotesteapi.mapper;

import org.example.produtotesteapi.dto.ClienteRequest;
import org.example.produtotesteapi.dto.ClienteResponse;
import org.example.produtotesteapi.model.Cliente;
import org.springframework.stereotype.Component;



@Component
public class ClienteMapper {


        public ClienteResponse toDto(Cliente cliente) {
            if (cliente == null) {
                return null;
            }

            return new ClienteResponse(
                    cliente.getId(),
                    cliente.getNome(),
                    cliente.getCpf(),
                    cliente.getEmail(),
                    cliente.getStatus(),
                    cliente.getEndereco(),
                    cliente.getCadastradoEm()
            );
        }


        public Cliente toModel(ClienteRequest request) {
            if (request == null) {
                return null;
            }

            Cliente cliente = new Cliente(
                    request.nome(),
                    request.cpf(),
                    request.email(),
                    request.password(),
                    request.telefone(),
                    request.endereco()
            );

            return cliente;
        }

}
