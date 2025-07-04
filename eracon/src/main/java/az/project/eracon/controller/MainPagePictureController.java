package az.project.eracon.controller;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.service.MainPagePictureService;
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
@RequestMapping("/api/main-page-pictures")
@RequiredArgsConstructor
public class MainPagePictureController {

    private final MainPagePictureService mediaService;

    @PostMapping(value = "/pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> uploadPictures(
            @RequestParam("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(mediaService.uploadPictures(files.toArray(new MultipartFile[0])));
    }

    @GetMapping("/pictures")
    public ResponseEntity<List<MediaResponse>> getAllPictures() {
        return ResponseEntity.ok(mediaService.getAllPictures());
    }

    @GetMapping("/picture/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> getPictureById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getPictureById(id));
    }

    // Update media by id (files only, no type param)
//    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<MediaResponse> updateMedia(
//            @PathVariable Long id,
//            @RequestParam("files") MultipartFile[] files) throws IOException {
//        return ResponseEntity.ok(mediaService.updateMedia(id, files));
//    }

    // Delete media by id
    @DeleteMapping("/picture/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deletePicture(@PathVariable Long id) {
        mediaService.deletePicture(id);
        return ResponseEntity.ok("Şəkil uğurla silindi.");
    }

}