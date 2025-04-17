package com.mongs.restaurant.controller;

import com.mongs.restaurant.domain.entity.Photo;
import com.mongs.restaurant.domain.dtos.PhotoDto;
import com.mongs.restaurant.mappers.PhotoMapper;
import com.mongs.restaurant.services.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable String id) {
        return photoService.getPhotoAsResource(id).map(
                photo ->
                        ResponseEntity.ok()
                                .contentType(
                                        MediaTypeFactory.getMediaType(photo)
                                                .orElse(MediaType.APPLICATION_OCTET_STREAM)
                                )
                                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                                .body(photo)
        ).orElse(ResponseEntity.notFound().build());

    }
}
