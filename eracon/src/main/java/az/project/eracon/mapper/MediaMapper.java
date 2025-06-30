package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.MediaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class MediaMapper {
    public static MediaResponse convertToDTO(MediaEntity media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setMediaUrls(media.getMediaUrls());

        return response;
    }

    public static List<MediaResponse> convertToDTOList(List<MediaEntity> medias) {
        return medias.stream()
                .map(MediaMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
