package az.project.eracon.controller;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@MyRestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {

    private final MediaService mediaService;

    @PostMapping(value = "/pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> uploadPictures(
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(mediaService.uploadPictures(files.toArray(new MultipartFile[0])));
    }

    @PostMapping(value = "/videos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> uploadVideos(
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(mediaService.uploadVideos(files.toArray(new MultipartFile[0])));
    }

    @PostMapping(value = "/documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> uploadDocuments(
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(mediaService.uploadDocuments(files.toArray(new MultipartFile[0])));
    }


    @GetMapping
    public ResponseEntity<List<MediaResponse>> getAll() {
        return ResponseEntity.ok(mediaService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getById(id));
    }

    // Update media by id (files only, no type param)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> updateMedia(
            @PathVariable Long id,
            @RequestParam("files") MultipartFile[] files) throws IOException {
        return ResponseEntity.ok(mediaService.updateMedia(id, files));
    }

    // Delete media by id
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
        return ResponseEntity.noContent().build();
    }
}