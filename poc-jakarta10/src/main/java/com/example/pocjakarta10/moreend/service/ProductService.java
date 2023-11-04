package com.example.pocjakarta10.moreend.service;

import com.example.pocjakarta10.moreend.model.Product;

import java.util.List;

public interface ProductService {

    Product save(Product p);
    Product update(Product p);
    List<Product> listAll();
    Product findById(Integer id);
    void delete(Integer id);
}
