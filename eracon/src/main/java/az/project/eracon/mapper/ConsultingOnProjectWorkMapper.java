package az.project.eracon.mapper;

import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.ConsultingOnProjectWorkEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ConsultingOnProjectWorkMapper {
    public static TitleDescriptionResponse convertToDTO(ConsultingOnProjectWorkEntity entity) {
        TitleDescriptionResponse dto = new TitleDescriptionResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescriptions(entity.getDescriptions());
        return dto;
    }

    public static List<TitleDescriptionResponse> convertToDTOList(List<ConsultingOnProjectWorkEntity> entities) {
        return entities.stream()
                .map(ConsultingOnProjectWorkMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
