package az.project.eracon.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class TitleDescriptionResponse {

    private Long id;
    private String title;
    private List<String> descriptions;
}
