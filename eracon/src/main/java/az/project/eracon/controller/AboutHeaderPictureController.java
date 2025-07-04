package az.project.eracon.controller;

import az.project.eracon.service.CompanyHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@MyRestController
@RequestMapping("/api/company-header-picture")
@RequiredArgsConstructor
public class AboutHeaderPictureController {

    private final CompanyHeaderPictureService service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> uploadPicture(@RequestParam("picture") MultipartFile picture) throws IOException {
        service.uploadPicture(picture);
        return ResponseEntity.ok().build();
    }

    // Delete current picture
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deletePicture(@PathVariable Long id) {
        service.deletePicture(id);
    }

    // Optional: Get current picture URL
    @GetMapping
    public String getCurrentPictureUrl() {
        return service.getPictureUrl();
    }
}
