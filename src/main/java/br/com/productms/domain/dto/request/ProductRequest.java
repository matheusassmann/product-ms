package br.com.productms.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
}
