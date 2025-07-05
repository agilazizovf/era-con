package az.project.eracon.controller;

import az.project.eracon.dto.request.AddProjectRequest;
import az.project.eracon.dto.request.UpdateProjectRequest;
import az.project.eracon.dto.response.MainImageResponse;
import az.project.eracon.dto.response.ProjectResponse;
import az.project.eracon.service.FileService;
import az.project.eracon.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@MyRestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    // ✅ Yeni layihə əlavə et
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProjectResponse add(@RequestBody AddProjectRequest request) {
        return projectService.add(request);
    }

    // ✅ Bütün layihələri getir
    @GetMapping
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    @GetMapping("/mainImage/{id}")
    public MainImageResponse getMainImage(@PathVariable Long id) {
        return projectService.getMainImage(id);
    }

//    @GetMapping("/images/{filename:.+}")
//    public ResponseEntity<Resource> getImage(@PathVariable String filename) throws IOException {
//        Resource file = fileService.loadFileAsResource("project_pictures", filename);
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_JPEG) // və ya MIME tipini avtomatik aşkarla
//                .body(file);
//    }

    // ✅ Layihə ID ilə getir
    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    // ✅ Layihəni yenilə
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProjectResponse update(@RequestBody UpdateProjectRequest request) {
        return projectService.update(request);
    }

    // ✅ Layihəni sil
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void delete(@PathVariable Long id) {
        projectService.delete(id);
    }

    @PostMapping(value = "/{projectId}/upload-main-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjectResponse uploadMainImage(@PathVariable Long projectId,
                                           @RequestParam("file") MultipartFile file) throws IOException {
        return projectService.uploadMainImage(projectId, file);
    }

    @PostMapping(value = "/{projectId}/upload-images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectResponse> uploadImages(
            @PathVariable Long projectId,
            @RequestPart("files") List<MultipartFile> files) throws IOException {

        ProjectResponse response = projectService.uploadImages(projectId, files);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/pictures/{pictureId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProjectResponse> updateImage(
            @PathVariable Long pictureId,
            @RequestParam("file") MultipartFile file) throws IOException {

        ProjectResponse response = projectService.updateImage(pictureId, file);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/pictures/{pictureId}")
    public ResponseEntity<Void> deletePicture(@PathVariable Long pictureId) {
        projectService.deletePicture(pictureId);
        return ResponseEntity.noContent().build();
    }

}
