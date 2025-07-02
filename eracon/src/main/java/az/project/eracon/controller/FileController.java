package az.project.eracon.controller;

import az.project.eracon.service.FileService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@MyRestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private Path rootLocation;
    private final FileService service;

    @PostConstruct
    private void init() {
        rootLocation = Paths.get(service.getFileLocation());
    }

    @GetMapping("/download/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> serveFile(@PathVariable String filename) {
        try {
            Resource file = loadAsResource(filename);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(file);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body("Fayl tapılmadı: " + filename);
        }
    }


    public Resource loadAsResource(String filename) {
        try {
            Path file = load(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Fayl mövcud deyil və ya oxunaqlı deyil: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL səhvdir: " + filename, e);
        }
    }


    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }
}
