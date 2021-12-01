package br.com.productms.controller;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.dto.response.ProductResponse;
import br.com.productms.domain.model.Product;
import br.com.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    public ResponseEntity<ProductResponse> insert(@RequestBody @Valid ProductRequest productRequest, UriComponentsBuilder uriBuilder) {
        Product product = service.save(productRequest);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(Product.toResponse(product)); //TODO retornar 201 E o ID gerado
    }

    @GetMapping()
    public ResponseEntity<ProductResponse> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(Product.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Validated ProductRequest product) {
        service.update(Product.from(product));
        return ResponseEntity.noContent().build(); //TODO
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(UUID id) {
        try {
            service.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<String> search(@RequestParam String description, @RequestParam String name){
        return ResponseEntity.ok(description);
    }


}
