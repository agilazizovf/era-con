package az.project.eracon.controller;

import az.project.eracon.dto.response.HeaderPictureResponse;
import az.project.eracon.service.MediaHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@MyRestController
@RequestMapping("/api/media-header-picture")
@RequiredArgsConstructor
public class MediaHeaderPictureController {

    private final MediaHeaderPictureService service;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<HeaderPictureResponse> uploadPicture(@RequestParam("picture") MultipartFile picture) throws IOException {
        service.uploadPicture(picture);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deletePicture(@PathVariable Long id) {
        service.deletePicture(id);
    }

    @GetMapping
    public HeaderPictureResponse getCurrentPictureUrl() {
        return service.getPictureUrl();
    }
}
