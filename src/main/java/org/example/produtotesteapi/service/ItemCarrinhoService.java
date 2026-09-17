package org.example.produtotesteapi.service;

import org.example.produtotesteapi.repository.ItemCarrinhoRepositorie;
import org.springframework.stereotype.Service;

@Service
public class ItemCarrinhoService {

    private final ItemCarrinhoRepositorie itemRepo;

    public ItemCarrinhoService(ItemCarrinhoRepositorie itemRepo) {
        this.itemRepo = itemRepo;
    }
}
