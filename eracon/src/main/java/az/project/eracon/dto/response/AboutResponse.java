package az.project.eracon.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AboutResponse {
    private Long id;
    private String title;
    private String description;
    private List<AboutPictureResponse> pictures;
}
