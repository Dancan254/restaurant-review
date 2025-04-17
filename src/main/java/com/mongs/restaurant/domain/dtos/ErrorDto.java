package com.mongs.restaurant.domain.dtos;

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
