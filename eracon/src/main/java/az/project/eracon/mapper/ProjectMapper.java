package az.project.eracon.mapper;

import az.project.eracon.dto.response.ProjectResponse;
import az.project.eracon.entity.ProjectEntity;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class ProjectMapper {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static ProjectResponse convertToDTO(ProjectEntity project) {
        ProjectResponse dto = new ProjectResponse();
        dto.setId(project.getId());
        dto.setTitle(project.getTitle());
        dto.setLocation(project.getLocation());
        dto.setArea(project.getArea());
        dto.setStartDate(project.getStartDate() != null ? project.getStartDate().format(formatter) : null);
        dto.setEndDate(project.getEndDate() != null
                ? project.getEndDate().format(formatter)
                : "Davam edir");
        dto.setMainImage(project.getMainImage());
        dto.setAdditionalImages(project.getAdditionalImages());

        return dto;
    }

    public static List<ProjectResponse> convertToDTOList(List<ProjectEntity> projects) {
        return projects.stream()
                .map(ProjectMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
