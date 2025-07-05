package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.dto.response.VideoResponse;
import az.project.eracon.entity.PictureEntity;
import az.project.eracon.entity.VideoEntity;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.stream.Collectors;

public class VideoMapper {
    public static VideoResponse convertToDTO(VideoEntity media) {
        VideoResponse response = new VideoResponse();
        response.setId(media.getId());
        response.setVideoUrl(media.getVideoUrl());

        return response;
    }

    public static List<VideoResponse> convertToDTOList(List<VideoEntity> medias) {
        return medias.stream()
                .map(VideoMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
