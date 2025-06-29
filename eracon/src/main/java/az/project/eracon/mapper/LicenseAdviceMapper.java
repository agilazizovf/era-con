package az.project.eracon.mapper;

import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.LicenseAdviceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class LicenseAdviceMapper {
    public static TitleDescriptionResponse convertToDTO(LicenseAdviceEntity licenseAdvice) {
        TitleDescriptionResponse response = new TitleDescriptionResponse();
        response.setId(licenseAdvice.getId());
        response.setTitle(licenseAdvice.getTitle());
        response.setDescriptions(licenseAdvice.getDescriptions());

        return response;
    }

    public static List<TitleDescriptionResponse> convertToDTOList(List<LicenseAdviceEntity> list) {
        return list.stream()
                .map(LicenseAdviceMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
