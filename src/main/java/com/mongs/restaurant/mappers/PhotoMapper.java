package com.mongs.restaurant.mappers;

import com.mongs.restaurant.domain.entity.Photo;
import com.mongs.restaurant.domain.dtos.PhotoDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
    PhotoDto toDto(Photo photo);
}
