package com.mongs.restaurant.domain.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {

    private String url;
    private LocalDateTime uploadDate;
}
