package az.project.eracon.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class MediaResponse {

    private Long id;

    private List<String> mediaUrls;
}
