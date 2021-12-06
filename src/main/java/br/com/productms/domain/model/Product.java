package br.com.productms.domain.model;

import br.com.productms.domain.dto.request.ProductRequest;
import br.com.productms.domain.dto.response.ProductResponse;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "products")
public class Product {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    public static Product from(ProductRequest request) {
        return Product.builder()
                .id(request.getId())
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .build();
    }

    public static ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }

    public static List<ProductResponse> toResponse(List<Product> products) {
        List<ProductResponse> listProducts = new ArrayList<>();
        products.forEach(product -> listProducts.add(Product.toResponse(product)));
        return listProducts;
    }
}
