package az.project.eracon.controller;

import az.project.eracon.service.ServiceHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@MyRestController
@RequestMapping("/api/service-header-picture")
@RequiredArgsConstructor
public class ServiceHeaderPictureController {

    private final ServiceHeaderPictureService service;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/upload")
    public ResponseEntity<Void> uploadPicture(@RequestParam("picture") MultipartFile picture) throws IOException {
        service.uploadPicture(picture);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deletePicture(@PathVariable Long id) {
        service.deletePicture(id);
    }

    @GetMapping
    public String getCurrentPictureUrl() {
        return service.getPictureUrl();
    }
}

