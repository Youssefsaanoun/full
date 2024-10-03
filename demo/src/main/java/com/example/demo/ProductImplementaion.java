package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ProductImplementaion implements ProductServise {

    private final ProductRepository productRepository;

    // Constructor Injection for ProductRepository
    public ProductImplementaion(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> FindProductByid(Long id) {
        return productRepository.findById(id);  // Retourne directement l'Optional, pas besoin de vérifier s'il est vide ici
    }

    @Override
    public Product UpdateProduct(Product product) {
        Optional<Product> optional = productRepository.findById(product.getId());
        if (optional.isEmpty()) {
            return null;  // Peut-être envisager de lancer une exception personnalisée ici pour un meilleur contrôle d'erreur
        } else {
            return productRepository.save(product);
        }
    }

    @Override
    public void DeleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product AjouteProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> GetAllProduct() {
        return productRepository.findAll();
    }
}
