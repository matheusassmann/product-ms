package br.com.productms.service;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.model.Product;
import br.com.productms.repository.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product findById(UUID id) {
       Product obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Product not found!"));
       return obj;
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product save(ProductRequest productRequest) {
        return repository.save(Product.from(productRequest));
    }

    public Product update(Product product, UUID id) {
        findById(id);
        product.setId(id);
        return repository.save(product);
    }

    public void delete(UUID id) {
        findById(id);
        repository.deleteById(id);
    }

    public List<Product> search(String nameOrDescription, BigDecimal minPrice, BigDecimal maxPrice) {
        return repository.search(nameOrDescription, minPrice, maxPrice);
    }
}
