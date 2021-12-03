package br.com.productms.controller;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.dto.response.ProductResponse;
import br.com.productms.domain.model.Product;
import br.com.productms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.math.BigDecimal;
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
        return ResponseEntity.created(uri).body(Product.toResponse(product));
    }

    @GetMapping()
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok(Product.toResponse(products));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(Product.toResponse(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest productRequest, @PathVariable UUID id, UriComponentsBuilder uriBuilder) {
        Product obj = service.update(Product.from(productRequest), id);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(Product.toResponse(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam(name = "q") String nameOrDescription,
                                                        @RequestParam(name = "min_price") BigDecimal minPrice,
                                                        @RequestParam(name = "max_price") BigDecimal maxPrice) {
        List<Product> listProduct = service.search(nameOrDescription, minPrice, maxPrice);
        return ResponseEntity.ok(Product.toResponse(listProduct));
    }

}
