package br.com.productms.controller;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.dto.response.ProductResponse;
import br.com.productms.domain.model.Product;
import br.com.productms.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("v1/products")
@Api(value = "Products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping()
    @ApiOperation(value = "Insert() Product")
    public ResponseEntity<ProductResponse> insert(@RequestBody @Valid ProductRequest productRequest, UriComponentsBuilder uriBuilder) {
        Product product = service.save(productRequest);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
        return ResponseEntity.created(uri).body(Product.toResponse(product));
    }

    @GetMapping()
    @ApiOperation(value = "ListAll() Products")
    public ResponseEntity<List<ProductResponse>> findAll() {
        List<Product> products = service.findAll();
        return ResponseEntity.ok(Product.toResponse(products));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "FindById() Product")
    public ResponseEntity<ProductResponse> findById(@PathVariable UUID id) {
        Product product = service.findById(id);
        return ResponseEntity.ok().body(Product.toResponse(product));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update() Product")
    public ResponseEntity<ProductResponse> update(@RequestBody @Valid ProductRequest productRequest, @PathVariable UUID id, UriComponentsBuilder uriBuilder) {
        Product obj = service.update(Product.from(productRequest), id);
        URI uri = uriBuilder.path("/products/{id}").buildAndExpand(productRequest.getId()).toUri();
        return ResponseEntity.created(uri).body(Product.toResponse(obj));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete() Product")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @ApiOperation(value = "Search() Products (q, min_price, max_price")
    public ResponseEntity<List<ProductResponse>> search(@RequestParam(name = "q") String nameOrDescription,
                                                        @RequestParam(name = "min_price") BigDecimal minPrice,
                                                        @RequestParam(name = "max_price") BigDecimal maxPrice) {
        List<Product> listProduct = service.search(nameOrDescription, minPrice, maxPrice);
        return ResponseEntity.ok(Product.toResponse(listProduct));
    }

}
