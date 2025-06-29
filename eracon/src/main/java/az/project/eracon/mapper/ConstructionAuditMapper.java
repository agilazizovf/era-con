package az.project.eracon.mapper;

import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.ConstructionAuditEntity;
import az.project.eracon.entity.LicenseAdviceEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ConstructionAuditMapper {
    public static TitleDescriptionResponse convertToDTO(ConstructionAuditEntity constructionAudit) {
        TitleDescriptionResponse response = new TitleDescriptionResponse();
        response.setId(constructionAudit.getId());
        response.setTitle(constructionAudit.getTitle());
        response.setDescriptions(constructionAudit.getDescriptions());

        return response;
    }

    public static List<TitleDescriptionResponse> convertToDTOList(List<ConstructionAuditEntity> list) {
        return list.stream()
                .map(ConstructionAuditMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
