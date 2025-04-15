package com.mongs.restaurant.controller;

import com.mongs.restaurant.domain.entities.Photo;
import com.mongs.restaurant.domain.entities.dtos.PhotoDto;
import com.mongs.restaurant.mappers.PhotoMapper;
import com.mongs.restaurant.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/photos")
public class PhotoController {

    private final PhotoService photoService;
    private final PhotoMapper photoMapper;

    @PostMapping("/upload")
    public ResponseEntity<PhotoDto> uploadPhoto(@RequestParam MultipartFile file) {
        Photo photo = photoService.uploadPhoto(file);

        return ResponseEntity.ok(photoMapper.toDto(photo));
    }
}
