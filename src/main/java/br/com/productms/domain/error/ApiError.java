package br.com.productms.domain.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    @JsonProperty("status_code")
    private Integer statusCode;

    private String message;
}
