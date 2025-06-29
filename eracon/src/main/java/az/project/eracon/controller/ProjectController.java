package az.project.eracon.controller;

import az.project.eracon.dto.request.AddProjectRequest;
import az.project.eracon.dto.request.UpdateProjectRequest;
import az.project.eracon.dto.response.ProjectResponse;
import az.project.eracon.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    public ProjectResponse add(@Valid @RequestBody AddProjectRequest request) {
        return projectService.add(request);
    }

    // ✅ Bütün layihələri getir
    @GetMapping
    public List<ProjectResponse> findAll() {
        return projectService.findAll();
    }

    // ✅ Layihə ID ilə getir
    @GetMapping("/{id}")
    public ProjectResponse findById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    // ✅ Layihəni yenilə
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ProjectResponse update(@Valid @RequestBody UpdateProjectRequest request) {
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
    public ProjectResponse uploadImages(@PathVariable Long projectId,
                                        @RequestParam("files") List<MultipartFile> files) throws IOException {
        return projectService.uploadImages(projectId, files);
    }


}
