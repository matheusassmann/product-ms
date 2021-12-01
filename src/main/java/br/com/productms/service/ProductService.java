package br.com.productms.service;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.model.Product;
import br.com.productms.repository.ProductRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Product update(Product product) {
        findById(product.getId());
        return repository.save(product);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
