package br.com.productms.domain.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;

}
