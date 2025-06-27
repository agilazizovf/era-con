package az.project.eracon.mapper;

import az.project.eracon.dto.response.AboutResponse;
import az.project.eracon.entity.AboutEntity;
import az.project.eracon.entity.AboutPictureEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AboutMapper {
    public static AboutResponse convertToDTO(AboutEntity entity) {
        AboutResponse dto = new AboutResponse();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());

        if (entity.getPictures() != null) {
            List<String> urls = entity.getPictures().stream()
                    .map(AboutPictureEntity::getFileName)
                    .toList();
            dto.setPictureUrls(urls);
        }

        return dto;
    }


    public static List<AboutResponse> convertToDTOList(List<AboutEntity> aboutEntities) {
        return aboutEntities.stream()
                .map(AboutMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
