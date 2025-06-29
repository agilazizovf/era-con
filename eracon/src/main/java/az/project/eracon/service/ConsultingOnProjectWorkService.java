package az.project.eracon.service;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.ConsultingOnProjectWorkEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.ConsultingOnProjectWorkMapper;
import az.project.eracon.repository.ConsultingOnProjectWorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultingOnProjectWorkService {

    private final ConsultingOnProjectWorkRepository repository;

    public TitleDescriptionResponse add(AddTitleDescriptionRequest request) {
        ConsultingOnProjectWorkEntity entity = new ConsultingOnProjectWorkEntity();
        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return ConsultingOnProjectWorkMapper.convertToDTO(entity);
    }

    public TitleDescriptionResponse findById(Long id) {
        ConsultingOnProjectWorkEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Layihə üzrə məsləhət” tapılmadı", "Consulting on project work not found",
                        "Not found", 404, null));

        return ConsultingOnProjectWorkMapper.convertToDTO(entity);
    }

    public List<TitleDescriptionResponse> findAll() {
        List<ConsultingOnProjectWorkEntity> list = repository.findAll();
        return ConsultingOnProjectWorkMapper.convertToDTOList(list);
    }

    public TitleDescriptionResponse update(UpdateTitleDescriptionRequest request) {
        ConsultingOnProjectWorkEntity entity = repository.findById(request.getId())
                .orElseThrow(() -> new CustomException("“Layihə üzrə məsləhət” tapılmadı", "Consulting on project work not found",
                        "Not found", 404, null));

        entity.setTitle(request.getTitle());
        entity.setDescriptions(request.getDescriptions());

        repository.save(entity);

        return ConsultingOnProjectWorkMapper.convertToDTO(entity);
    }

    public void delete(Long id) {
        ConsultingOnProjectWorkEntity entity = repository.findById(id)
                .orElseThrow(() -> new CustomException("“Layihə üzrə məsləhət” tapılmadı", "Consulting on project work not found",
                        "Not found", 404, null));

        repository.delete(entity);
    }
}
