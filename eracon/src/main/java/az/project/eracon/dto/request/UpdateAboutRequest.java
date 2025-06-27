package az.project.eracon.dto.request;

import lombok.Data;

@Data
public class UpdateAboutRequest {

    private Long id;
    private String title;
    private String description;
}
