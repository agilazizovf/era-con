package az.project.eracon.service;

import az.project.eracon.dto.request.AddProjectRequest;
import az.project.eracon.dto.request.UpdateProjectRequest;
import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.MainImageResponse;
import az.project.eracon.dto.response.ProjectResponse;
import az.project.eracon.entity.ProjectEntity;
import az.project.eracon.entity.ProjectPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.ProjectMapper;
import az.project.eracon.repository.ProjectPictureRepository;
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
    private final ProjectPictureRepository projectPictureRepository;
    private final FileService fileService;
    private final CloudinaryService cloudinaryService;

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

        ResponseEntity<FileResponse> response = cloudinaryService.uploadFile(file);
        String fileName = response.getBody().getUuidName();

        project.setMainImage(fileName);
        projectRepository.save(project);


        return ProjectMapper.convertToDTO(project);
    }

    public MainImageResponse getMainImage(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found", 404, null));
        MainImageResponse imageResponse = new MainImageResponse();
        imageResponse.setMainImageUrl(project.getMainImage());

        return imageResponse;
    }

    public ProjectResponse uploadImages(Long projectId, List<MultipartFile> files) throws IOException {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new CustomException("Layihə tapılmadı", "Project not found", "Not found", 404, null));

        List<ProjectPictureEntity> newPictures = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = cloudinaryService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();

            ProjectPictureEntity picture = new ProjectPictureEntity();
            picture.setFileName(newFileName);
            picture.setProject(project);
            newPictures.add(picture);
        }

        // Əgər ProjectEntity-də pictures sahəsi varsa (List<ProjectPictureEntity>)
        project.getPictures().addAll(newPictures);

        projectRepository.save(project);

        return ProjectMapper.convertToDTO(project);
    }
    public ProjectResponse updateImage(Long pictureId, MultipartFile file) throws IOException {
        ProjectPictureEntity picture = projectPictureRepository.findById(pictureId)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not found", 404, null));

        String oldFileName = picture.getFileName();

        ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file);
        String newFileName = uploaded.getBody().getUuidName();

        picture.setFileName(newFileName);

        projectPictureRepository.save(picture);

        fileService.deleteFile(oldFileName);

        ProjectEntity project = picture.getProject();

        return ProjectMapper.convertToDTO(project);
    }
    public void deletePicture(Long pictureId) {
        ProjectPictureEntity picture = projectPictureRepository.findById(pictureId)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not found", 404, null));

        try {
            fileService.deleteFile(picture.getFileName()); // faylı diskdən sil
        } catch (CustomException e) {
            System.err.println("Failed to delete file: " + picture.getFileName() + " - " + e.getMessage());
        }

        projectPictureRepository.delete(picture); // DB-dən sil
    }



}
