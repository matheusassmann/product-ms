package br.com.productms.service;

import br.com.productms.domain.model.Product;
import br.com.productms.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductService {

    @Autowired
    ProductRepository repository;

    public Optional<Product> findById(UUID id) {
        return repository.findById(id);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(Product product) {
        product = repository.save(product);
        return product;
    }

    public Product update(Product product) {
        findById(product.getId());
        return repository.save(product);
    }

    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }
}
