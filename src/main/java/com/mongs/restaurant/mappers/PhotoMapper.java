package com.mongs.restaurant.mappers;

import com.mongs.restaurant.domain.entities.Photo;
import com.mongs.restaurant.domain.entities.dtos.PhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}
