package az.project.eracon.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ProjectResponse {

    private Long id;

    private String location;
    private String title;
    private double area;

    private String startDate;
    private String endDate;

    private String mainImage;
    private List<String> additionalImages;
}
