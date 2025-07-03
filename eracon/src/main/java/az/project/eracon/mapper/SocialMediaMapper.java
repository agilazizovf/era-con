package az.project.eracon.mapper;

import az.project.eracon.dto.response.SocialMediaResponse;
import az.project.eracon.entity.SocialMediaEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SocialMediaMapper {
    public static SocialMediaResponse convertToDTO(SocialMediaEntity socialMedia) {
        SocialMediaResponse response = new SocialMediaResponse();
        response.setId(socialMedia.getId());
        response.setUrl(socialMedia.getUrl());

        return response;
    }

    public static List<SocialMediaResponse> convertToDTOList(List<SocialMediaEntity> socialMediaEntities) {
        return socialMediaEntities.stream()
                .map(SocialMediaMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
