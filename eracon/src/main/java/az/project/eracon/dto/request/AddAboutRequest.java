package az.project.eracon.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddAboutRequest {
    private String title;
    private String description;
    private List<AboutPictureRequest> pictures;
}
