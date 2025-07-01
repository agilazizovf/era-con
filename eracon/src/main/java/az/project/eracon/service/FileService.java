package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.exception.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileService {

    @Value("${file.upload-dir}")
    private String fileLocation;

    @PostConstruct
    public void init() {
        try {
            Path uploadPath = Paths.get(fileLocation);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }


    public Resource loadFileAsResource(String folder, String filename) {
        try {
            Path filePath = Paths.get("uploads")
                    .resolve(folder)
                    .resolve(filename)
                    .normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("Fayl tapılmadı: " + filename);
            }
        } catch (MalformedURLException | FileNotFoundException ex) {
            throw new RuntimeException("Fayl oxuna bilmədi: " + filename, ex);
        }
    }

    public ResponseEntity<FileResponse> uploadFile(MultipartFile file, String subFolder) throws IOException {
        if (file.getSize() > 30_000_000) {
            throw new CustomException("Faylın ölçüsü çoxdur. Maksimum 30 MB", "File size is too big", "Bad Request", 400, null);
        }

        // Create dynamic folder inside base path
        Path fullPath = Paths.get(fileLocation, subFolder);
        if (!Files.exists(fullPath)) {
            Files.createDirectories(fullPath);
        }

        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uuidName = UUID.randomUUID() + extension;

        Path targetPath = fullPath.resolve(uuidName);
        try (InputStream stream = file.getInputStream()) {
            Files.copy(stream, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }

        FileResponse created = new FileResponse(uuidName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuidName}")
                .buildAndExpand(created.getUuidName()).toUri();

        return ResponseEntity.created(location).body(created);
    }


    public void deleteFile(String fileName, String subFolder) {
        Path filePath = Paths.get(fileLocation, subFolder, fileName);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new CustomException("Fayl silinmədi", "File deletion failed", "Bad Request", 400, null);
        }
    }



    // ✅ This method fixes the missing getter
    public String getFileLocation() {
        return fileLocation;
    }
}
