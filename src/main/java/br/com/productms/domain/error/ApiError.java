package br.com.productms.domain.error;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private Integer statusCode; //TODO Snakecase
    private String message;
}
