package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Product")
@CrossOrigin(origins = "http://localhost:5173")  // Correction de l'URL
public class ProductController {

    @Autowired
    private ProductImplementaion productImplementaion;

    @GetMapping("/getall")
    public List<Product> getAllProducts() {
        return productImplementaion.GetAllProduct();
    }

    @GetMapping("/getByid/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productImplementaion.FindProductByid(id);
    }

    @PostMapping("/ajouteProduct")  // Correction de la faute de frappe
    public Product ajouteProduct(@RequestBody Product product) {
        return productImplementaion.AjouteProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productImplementaion.DeleteProduct(id);
    }

    @PutMapping("/changeProduct/{id}")
    public ResponseEntity<Product> changeProduct(@PathVariable Long id, @RequestBody Product productUpdated) {
        Optional<Product> existingProduct = productImplementaion.FindProductByid(id);

        if (existingProduct.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            Product product = existingProduct.get();
            product.setNom(productUpdated.getNom());
            product.setPrice(productUpdated.getPrice());
            return ResponseEntity.ok(productImplementaion.UpdateProduct(product));
        }
    }
}
