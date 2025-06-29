package az.project.eracon.service;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.entity.LicenseAdviceEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.LicenseAdviceMapper;
import az.project.eracon.repository.LicenseAdviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenseAdviceService {

    private final LicenseAdviceRepository licenseAdviceRepository;

    public TitleDescriptionResponse add(AddTitleDescriptionRequest request) {
        LicenseAdviceEntity licenseAdvice = new LicenseAdviceEntity();
        licenseAdvice.setTitle(request.getTitle());
        licenseAdvice.setDescriptions(request.getDescriptions());

        licenseAdviceRepository.save(licenseAdvice);

        return LicenseAdviceMapper.convertToDTO(licenseAdvice);
    }

    public TitleDescriptionResponse findById(Long id) {
        LicenseAdviceEntity licenseAdvice = licenseAdviceRepository.findById(id)
                .orElseThrow(() -> new CustomException("\"Lisenziya məsləhəti\" tapılmadı", "\"License advice\" not found",
                        "Not found", 404, null));
        return LicenseAdviceMapper.convertToDTO(licenseAdvice);
    }

    public List<TitleDescriptionResponse> findAll() {
        List<LicenseAdviceEntity> list = licenseAdviceRepository.findAll();
        return LicenseAdviceMapper.convertToDTOList(list);
    }

    public TitleDescriptionResponse update(UpdateTitleDescriptionRequest request) {
        LicenseAdviceEntity licenseAdvice = licenseAdviceRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("\"Lisenziya məsləhəti\" tapılmadı", "\"License advice\" not found",
                        "Not found", 404, null));
        licenseAdvice.setTitle(request.getTitle());
        licenseAdvice.setDescriptions(request.getDescriptions());

        licenseAdviceRepository.save(licenseAdvice);

        return LicenseAdviceMapper.convertToDTO(licenseAdvice);
    }

    public void delete(Long id) {
        LicenseAdviceEntity licenseAdvice = licenseAdviceRepository.findById(id)
                .orElseThrow(() -> new CustomException("\"Lisenziya məsləhəti\" tapılmadı", "\"License advice\" not found",
                        "Not found", 404, null));
        licenseAdviceRepository.delete(licenseAdvice);
    }
}
