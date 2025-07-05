package az.project.eracon.controller;

import az.project.eracon.dto.request.AddAboutRequest;
import az.project.eracon.dto.request.UpdateAboutRequest;
import az.project.eracon.dto.response.AboutResponse;
import az.project.eracon.service.AboutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@MyRestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutController {

    private final AboutService aboutService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AboutResponse> add(@RequestBody AddAboutRequest request) {
        return ResponseEntity.ok(aboutService.add(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AboutResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(aboutService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AboutResponse>> findAll() {
        return ResponseEntity.ok(aboutService.findAll());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<AboutResponse> update(@RequestBody UpdateAboutRequest request) {
        return ResponseEntity.ok(aboutService.update(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        aboutService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/pictures", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<AboutResponse>> uploadPictures(
            @PathVariable Long id,
            @RequestPart("files") List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(aboutService.uploadPictures(id, files));
    }

    @PutMapping(value = "/{pictureId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AboutResponse> updatePicture(
            @PathVariable Long pictureId,
            @RequestParam("file") MultipartFile file) throws IOException {

        AboutResponse updatedAbout = aboutService.updatePicture(pictureId, file);
        return ResponseEntity.ok(updatedAbout);
    }

    @DeleteMapping("/delete-picture/{pictureId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteSinglePicture(@PathVariable Long pictureId) {
        aboutService.deletePicture(pictureId);
        return ResponseEntity.noContent().build(); // HTTP 204 No Content
    }

}