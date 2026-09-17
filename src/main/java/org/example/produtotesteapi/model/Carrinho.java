package org.example.produtotesteapi.model;

import jakarta.persistence.*;
import org.example.produtotesteapi.enums.StatusCarrinho;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "carrinhos")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Cliente cliente;

    private LocalDate dataCriacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<ItemCarrinho> itemCarrinho;

    private LocalDate dataAtualizacao;
    private StatusCarrinho status;

    public Carrinho() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public List<ItemCarrinho> getItemCarrinho(){
        return itemCarrinho;
    }

    public void setItemCarrinho(List<ItemCarrinho> itemCarrinho) {
        this.itemCarrinho = itemCarrinho;
    }

    public LocalDate getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDate dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public StatusCarrinho getStatus() {
        return status;
    }

    public void setStatus(StatusCarrinho status) {
        this.status = status;
    }

    public Carrinho(Cliente cliente, List<ItemCarrinho> itemCarrinho){
        this.cliente = cliente;
        this.itemCarrinho = itemCarrinho;
        this.status = StatusCarrinho.ABERTO;
        this.dataCriacao = LocalDate.now();
        }
}
