package az.project.eracon.dto.request;

import lombok.Data;

@Data
public class UpdateAboutPictureRequest {
    private Long id;
    private String url; // This will match with fileName in entity
}