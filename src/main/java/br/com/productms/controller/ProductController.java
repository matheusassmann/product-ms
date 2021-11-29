package br.com.productms.controller;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.dto.response.ProductResponse;
import br.com.productms.domain.model.Product;
import br.com.productms.service.ProductService;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/products")
    public ResponseEntity<ProductResponse> insert(@RequestBody @Validated ProductRequest product, UriBuilder uriBuilder){
        service.save(Product.from(product));
        URI uri = uriBuilder.path("products/{id}").build();
        return ResponseEntity.created(uri).build(); //TODO retornar 201 E o ID gerado
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Validated ProductRequest product){
        service.update(Product.from(product));
        return ResponseEntity.noContent().build(); //TODO
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductResponse> findById(@RequestParam UUID id) {
        Optional<Product> product = service.findById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products")
    public ResponseEntity<ProductResponse> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/products/search")
//    public ResponseEntity<ProductResponse> search(){
//    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> delete (UUID id){
        try{
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }


}
