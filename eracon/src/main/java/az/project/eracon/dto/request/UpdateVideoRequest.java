package az.project.eracon.dto.request;

import lombok.Data;

@Data
public class UpdateVideoRequest {
    private Long id;
    private String videoUrl;
}
