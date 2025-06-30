package az.project.eracon.mapper;

import az.project.eracon.dto.response.PartnerResponse;
import az.project.eracon.entity.PartnerEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PartnerMapper {
    public static PartnerResponse convertToDTO(PartnerEntity partner) {
        PartnerResponse response = new PartnerResponse();
        response.setId(partner.getId());
        response.setPictureUrl(partner.getPictureUrl());
        response.setWebSiteUrl(partner.getWebSiteUrl());

        return response;
    }

    public static List<PartnerResponse> convertToDTOList(List<PartnerEntity> partners) {
        return partners.stream()
                .map(PartnerMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
