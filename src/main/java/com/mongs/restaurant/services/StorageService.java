package com.mongs.restaurant.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface StorageService {

    String storeFile(MultipartFile file, String fileName);
    Optional<Resource> loadFileAsResource(String id);
}
