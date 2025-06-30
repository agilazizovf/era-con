package az.project.eracon.service;

import az.project.eracon.dto.request.AddProjectRequest;
import az.project.eracon.dto.request.UpdateProjectRequest;
import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.ProjectResponse;
import az.project.eracon.entity.ProjectEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.ProjectMapper;
import az.project.eracon.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final FileService fileService;

    public ProjectResponse add(AddProjectRequest request) {
        ProjectEntity project = new ProjectEntity();
        project.setTitle(request.getTitle());
        project.setLocation(request.getLocation());
        project.setArea(request.getArea());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());

        projectRepository.save(project);

        return ProjectMapper.convertToDTO(project);
    }

    public List<ProjectResponse> findAll() {
        List<ProjectEntity> projectEntities = projectRepository.findAll();
        return ProjectMapper.convertToDTOList(projectEntities);
    }

    public ProjectResponse findById(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found",
                        404, null));
        return ProjectMapper.convertToDTO(project);
    }

    public ProjectResponse update(UpdateProjectRequest request) {
        ProjectEntity project = projectRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found",
                        404, null));
        project.setTitle(request.getTitle());
        project.setLocation(request.getLocation());
        project.setArea(request.getArea());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());

        projectRepository.save(project);

        return ProjectMapper.convertToDTO(project);
    }

    public void delete(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found",
                        404, null));
        projectRepository.delete(project);
    }

    public ProjectResponse uploadMainImage(Long projectId, MultipartFile file) throws IOException {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found", 404, null));

        ResponseEntity<FileResponse> response = fileService.uploadFile(file, "project_pictures");
        String fileName = response.getBody().getUuidName();

        project.setMainImage(fileName);
        projectRepository.save(project);

        return ProjectMapper.convertToDTO(project);
    }

    public ProjectResponse uploadImages(Long projectId, List<MultipartFile> files) throws IOException {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found", 404, null));

        List<String> imagePaths = new ArrayList<>();
        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> response = fileService.uploadFile(file, "project_pictures");
            imagePaths.add(response.getBody().getUuidName());
        }

        List<String> existing = project.getAdditionalImages() != null
                ? new ArrayList<>(project.getAdditionalImages())
                : new ArrayList<>();

        existing.addAll(imagePaths);
        project.setAdditionalImages(existing);
        projectRepository.save(project);

        return ProjectMapper.convertToDTO(project);
    }


}
