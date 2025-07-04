package az.project.eracon.controller;

import az.project.eracon.service.MediaHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@MyRestController
@RequestMapping("/api/media-header-picture")
@RequiredArgsConstructor
public class MediaHeaderPictureController {

    private final MediaHeaderPictureService service;

    @PostMapping("/upload")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void uploadPicture(@RequestParam("pictureUrl") String pictureUrl) {
        service.uploadPicture(pictureUrl);
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
