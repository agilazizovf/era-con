package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.exception.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
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

    public ResponseEntity<FileResponse> uploadFile(MultipartFile file) throws IOException {
        InputStream stream = file.getInputStream();
        String originalFilename = file.getOriginalFilename();

        if (file.getSize() > 30_000_000) {
            throw new CustomException("Faylın ölçüsü çoxdur. Maksimum 30 MB", "File size is too big", "Bad Request", 400, null);
        }

        UUID uuid = UUID.randomUUID();
        String randomName = uuid.toString();
        String fileNameWithoutExtension = originalFilename.substring(0, originalFilename.lastIndexOf('.'));
        String fileRandomName = originalFilename.replace(fileNameWithoutExtension, randomName);

        Files.copy(stream, Paths.get(fileLocation, fileRandomName), StandardCopyOption.REPLACE_EXISTING);

        FileResponse created = new FileResponse(fileRandomName);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuidName}")
                .buildAndExpand(created.getUuidName()).toUri();

        return ResponseEntity.created(location).body(created);
    }

    public void deleteFile(String fileName) {
        Path filePath = Paths.get(fileLocation, fileName);
        try {
            if (Files.exists(filePath)) {
                Files.delete(filePath);
            }
        } catch (IOException e) {
            throw new CustomException("File deletion error", "Unable to delete file", "Bad Request", 400, null);
        }
    }


    // ✅ This method fixes the missing getter
    public String getFileLocation() {
        return fileLocation;
    }
}
