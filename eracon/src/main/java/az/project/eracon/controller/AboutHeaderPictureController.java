package az.project.eracon.controller;

import az.project.eracon.service.CompanyHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@MyRestController
@RequestMapping("/api/company-header-picture")
@RequiredArgsConstructor
public class AboutHeaderPictureController {

    private final CompanyHeaderPictureService service;

    // Upload or replace picture
    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void uploadPicture(@RequestParam("pictureUrl") String pictureUrl) {
        service.uploadPicture(pictureUrl);
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
