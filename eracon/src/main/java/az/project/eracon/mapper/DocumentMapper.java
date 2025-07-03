package az.project.eracon.mapper;

import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.DocumentEntity;
import az.project.eracon.entity.VideoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class DocumentMapper {
    public static MediaResponse convertToDTO(DocumentEntity media) {
        MediaResponse response = new MediaResponse();
        response.setId(media.getId());
        response.setMediaUrls(media.getMediaUrls());

        return response;
    }

    public static List<MediaResponse> convertToDTOList(List<DocumentEntity> medias) {
        return medias.stream()
                .map(DocumentMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
