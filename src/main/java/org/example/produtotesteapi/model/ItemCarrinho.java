package org.example.produtotesteapi.model;

import jakarta.persistence.*;

@Entity
@Table(name = "item_carrinhos")
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Carrinho carrinho;
    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Column(name = "quantidade")
    private Integer quantidade;


}
