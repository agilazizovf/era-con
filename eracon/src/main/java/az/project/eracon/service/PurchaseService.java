package az.project.eracon.service;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.PurchaseEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.PurchaseMapper;
import az.project.eracon.repository.PurchaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository repository;

    public TitleDescriptionResponse add(AddTitleDescriptionRequest request) {
        PurchaseEntity entity = new PurchaseEntity();
        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return PurchaseMapper.convertToDTO(entity);
    }

    public TitleDescriptionResponse findById(Long id) {
        PurchaseEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Satınalma” tapılmadı", "Purchase not found",
                        "Not found", 404, null));

        return PurchaseMapper.convertToDTO(entity);
    }

    public List<TitleDescriptionResponse> findAll() {
        List<PurchaseEntity> list = repository.findAll();
        return PurchaseMapper.convertToDTOList(list);
    }

    public TitleDescriptionResponse update(UpdateTitleDescriptionRequest request) {
        PurchaseEntity entity = repository.findById(request.getId())
                .orElseThrow(() -> new CustomException("“Satınalma” tapılmadı", "Purchase not found",
                        "Not found", 404, null));

        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return PurchaseMapper.convertToDTO(entity);
    }

    public void delete(Long id) {
        PurchaseEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Satınalma” tapılmadı", "Purchase not found",
                        "Not found", 404, null));

        repository.delete(entity);
    }
}
