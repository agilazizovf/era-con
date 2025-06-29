package az.project.eracon.mapper;

import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.ConstructionAuditEntity;
import az.project.eracon.entity.PurchaseEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PurchaseMapper {
    public static TitleDescriptionResponse convertToDTO(PurchaseEntity purchase) {
        TitleDescriptionResponse response = new TitleDescriptionResponse();
        response.setId(purchase.getId());
        response.setTitle(purchase.getTitle());
        response.setDescriptions(purchase.getDescriptions());

        return response;
    }

    public static List<TitleDescriptionResponse> convertToDTOList(List<PurchaseEntity> list) {
        return list.stream()
                .map(PurchaseMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
