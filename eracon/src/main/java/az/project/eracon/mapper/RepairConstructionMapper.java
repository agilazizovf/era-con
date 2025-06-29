package az.project.eracon.mapper;

import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.LicenseAdviceEntity;
import az.project.eracon.entity.RepairConstructionEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RepairConstructionMapper {
    public static TitleDescriptionResponse convertToDTO(RepairConstructionEntity repairConstruction) {
        TitleDescriptionResponse response = new TitleDescriptionResponse();
        response.setId(repairConstruction.getId());
        response.setTitle(repairConstruction.getTitle());
        response.setDescriptions(repairConstruction.getDescriptions());

        return response;
    }

    public static List<TitleDescriptionResponse> convertToDTOList(List<RepairConstructionEntity> list) {
        return list.stream()
                .map(RepairConstructionMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
