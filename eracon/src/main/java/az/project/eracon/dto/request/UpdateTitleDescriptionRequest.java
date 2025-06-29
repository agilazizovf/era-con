package az.project.eracon.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UpdateTitleDescriptionRequest {

    private Long id;
    private String title;
    private List<String> descriptions;
}
