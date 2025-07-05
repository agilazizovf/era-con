package az.project.eracon.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateAboutRequest {
    private Long id;
    private String title;
    private String description;
    private List<UpdateAboutPictureRequest> pictures;
}