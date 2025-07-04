package az.project.eracon.controller;

import az.project.eracon.entity.ProjectHeaderPictureEntity;
import az.project.eracon.service.ProjectHeaderPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@MyRestController
@RequestMapping("/api/project-header-picture")
@RequiredArgsConstructor
public class ProjectHeaderPictureController {

    private final ProjectHeaderPictureService service;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProjectHeaderPictureEntity uploadPicture(@RequestParam("picture") MultipartFile picture) throws IOException {
        return service.uploadPicture(picture);
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
