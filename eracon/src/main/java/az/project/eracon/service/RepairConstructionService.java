package az.project.eracon.service;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.RepairConstructionEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.RepairConstructionMapper;
import az.project.eracon.repository.RepairConstructionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RepairConstructionService {

    private final RepairConstructionRepository repository;

    public TitleDescriptionResponse add(AddTitleDescriptionRequest request) {
        RepairConstructionEntity entity = new RepairConstructionEntity();
        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return RepairConstructionMapper.convertToDTO(entity);
    }

    public TitleDescriptionResponse findById(Long id) {
        RepairConstructionEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Təmir və tikinti” tapılmadı",
                        "Repair and construction not found",
                        "Not found", 404, null));

        return RepairConstructionMapper.convertToDTO(entity);
    }

    public List<TitleDescriptionResponse> findAll() {
        List<RepairConstructionEntity> list = repository.findAll();
        return RepairConstructionMapper.convertToDTOList(list);
    }

    public TitleDescriptionResponse update(UpdateTitleDescriptionRequest request) {
        RepairConstructionEntity entity = repository.findById(request.getId())
                .orElseThrow(() -> new CustomException("“Təmir və tikinti” tapılmadı",
                        "Repair and construction not found",
                        "Not found", 404, null));

        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return RepairConstructionMapper.convertToDTO(entity);
    }

    public void delete(Long id) {
        RepairConstructionEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Təmir və tikinti” tapılmadı",
                        "Repair and construction not found",
                        "Not found", 404, null));

        repository.delete(entity);
    }
}