package io.github.vladfarias.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ApiErrorDTO {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
}