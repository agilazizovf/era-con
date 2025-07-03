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


    @GetMapping("/pictures")
    public ResponseEntity<List<MediaResponse>> getAllPictures() {
        return ResponseEntity.ok(mediaService.getAllPictures());
    }
    @GetMapping("/videos")
    public ResponseEntity<List<MediaResponse>> getAllVideos() {
        return ResponseEntity.ok(mediaService.getAllVideos());
    }
    @GetMapping("/documents")
    public ResponseEntity<List<MediaResponse>> getAllDocuments() {
        return ResponseEntity.ok(mediaService.getAllDocuments());
    }

    @GetMapping("/picture/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> getPictureById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getPictureById(id));
    }
    @GetMapping("/video/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> getVideoById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getVideoById(id));
    }
    @GetMapping("/document/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MediaResponse> getDocumentById(@PathVariable Long id) {
        return ResponseEntity.ok(mediaService.getDocumentById(id));
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
    @DeleteMapping("/video/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteVideo(@PathVariable Long id) {
        mediaService.deleteVideo(id);
        return ResponseEntity.ok("Video uğurla silindi.");
    }
    @DeleteMapping("/document/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteDocument(@PathVariable Long id) {
        mediaService.deleteDocument(id);
        return ResponseEntity.ok("Sənəd uğurla silindi.");
    }

}