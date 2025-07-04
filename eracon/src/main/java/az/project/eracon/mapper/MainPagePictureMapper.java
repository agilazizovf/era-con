package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.MainPagePictureEntity;
import az.project.eracon.entity.PictureEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MainPagePictureMapper {
    public static MediaResponse convertToDTO(MainPagePictureEntity media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setMediaUrls(media.getMediaUrls());

        return response;
    }

    public static List<MediaResponse> convertToDTOList(List<MainPagePictureEntity> medias) {
        return medias.stream()
                .map(MainPagePictureMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
