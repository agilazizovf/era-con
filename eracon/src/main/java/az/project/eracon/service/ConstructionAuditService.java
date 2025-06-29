package az.project.eracon.service;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.ConstructionAuditEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.ConstructionAuditMapper;
import az.project.eracon.repository.ConstructionAuditRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConstructionAuditService {

    private final ConstructionAuditRepository repository;

    public TitleDescriptionResponse add(AddTitleDescriptionRequest request) {
        ConstructionAuditEntity entity = new ConstructionAuditEntity();
        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return ConstructionAuditMapper.convertToDTO(entity);
    }

    public TitleDescriptionResponse findById(Long id) {
        ConstructionAuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Tikinti auditi” tapılmadı", "Construction audit not found",
                        "Not found", 404, null));

        return ConstructionAuditMapper.convertToDTO(entity);
    }

    public List<TitleDescriptionResponse> findAll() {
        List<ConstructionAuditEntity> list = repository.findAll();
        return ConstructionAuditMapper.convertToDTOList(list);
    }

    public TitleDescriptionResponse update(UpdateTitleDescriptionRequest request) {
        ConstructionAuditEntity entity = repository.findById(request.getId())
                .orElseThrow(() -> new CustomException("“Tikinti auditi” tapılmadı", "Construction audit not found",
                        "Not found", 404, null));

        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return ConstructionAuditMapper.convertToDTO(entity);
    }

    public void delete(Long id) {
        ConstructionAuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Tikinti auditi” tapılmadı", "Construction audit not found",
                        "Not found", 404, null));

        repository.delete(entity);
    }
}