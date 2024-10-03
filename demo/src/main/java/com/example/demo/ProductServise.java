package com.example.demo;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public interface ProductServise {
    @Autowired
    public Optional <Product> FindProductByid(Long id);
    public List<Product> GetAllProduct();
    public Product UpdateProduct(Product product);
    public void DeleteProduct(Long id);
    public Product AjouteProduct(Product product);
}
