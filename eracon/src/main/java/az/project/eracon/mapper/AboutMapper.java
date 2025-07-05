package az.project.eracon.mapper;

import az.project.eracon.dto.response.AboutPictureResponse;
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
            List<AboutPictureResponse> pictureDTOs = entity.getPictures().stream().map(picture -> {
                AboutPictureResponse picDto = new AboutPictureResponse();
                picDto.setId(picture.getId());
                picDto.setUrl(picture.getFileName());
                return picDto;
            }).collect(Collectors.toList());
            dto.setPictures(pictureDTOs);
        }

        return dto;
    }

    public static List<AboutResponse> convertToDTOList(List<AboutEntity> entities) {
        return entities.stream().map(AboutMapper::convertToDTO).collect(Collectors.toList());
    }
}