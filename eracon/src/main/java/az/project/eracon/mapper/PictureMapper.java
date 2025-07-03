package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.PictureEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PictureMapper {
    public static MediaResponse convertToDTO(PictureEntity media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setMediaUrls(media.getMediaUrls());

        return response;
    }

    public static List<MediaResponse> convertToDTOList(List<PictureEntity> medias) {
        return medias.stream()
                .map(PictureMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
