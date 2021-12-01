package br.com.productms.domain.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {

    private UUID id;

    @NotBlank(message = "must not be empty or null")
    private String name;

    @NotBlank(message = "must not be empty or null")
    private String description;

    @Positive(message = "must be higher then 0 and a positive value")
    @NotNull(message = "must not be null")
    private BigDecimal price;
}
