package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.PictureEntity;
import az.project.eracon.entity.VideoEntity;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.stream.Collectors;

public class VideoMapper {
    public static MediaResponse convertToDTO(VideoEntity media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setMediaUrls(media.getMediaUrls());

        return response;
    }

    public static List<MediaResponse> convertToDTOList(List<VideoEntity> medias) {
        return medias.stream()
                .map(VideoMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
