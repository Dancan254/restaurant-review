package com.mongs.restaurant.services.impl;

import com.mongs.restaurant.exceptions.StorageException;
import com.mongs.restaurant.services.StorageService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileSystemStorageService implements StorageService {

    @Value("${app.storage.location:uploads}")
    private String storageLocation;

    private Path rootLocation;

    @PostConstruct
    public void init() {
        // Initialize the storage location
        rootLocation = Paths.get(storageLocation);

        // Create the directory if it doesn't exist
        try {
            if (!rootLocation.toFile().exists()) {
                Files.createDirectories(rootLocation);
            }
        } catch (IOException e) {
            throw new StorageException("Failed to initialize storage location", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file ");
        }

        String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
        String finalFileName = fileName + "." + extension;

        Path destinationFilePath = rootLocation.resolve(Paths.get(finalFileName))
                .normalize()
                .toAbsolutePath();
        if (!destinationFilePath.getParent().equals(rootLocation.toAbsolutePath())) {
            // This is a security check to prevent directory traversal attacks
            throw new StorageException("Cannot store file outside current directory");
        }
        try(InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            return finalFileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + finalFileName, e);
        }
    }

    @Override
    public Optional<Resource> loadFileAsResource(String filename) {
        Path file = rootLocation.resolve(filename);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return Optional.of(resource);
            } else {
                return Optional.empty();
            }
        } catch (MalformedURLException e){
            log.warn("Failed to load file as resource: {}", filename, e);
            return Optional.empty();
        }
    }
}
