package com.mongs.restaurant.domain.entities.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private Integer status;
    private String message;
}
