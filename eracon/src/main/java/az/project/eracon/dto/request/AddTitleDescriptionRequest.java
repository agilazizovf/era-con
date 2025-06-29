package az.project.eracon.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class AddTitleDescriptionRequest {

    private String title;
    private List<String> descriptions;
}
