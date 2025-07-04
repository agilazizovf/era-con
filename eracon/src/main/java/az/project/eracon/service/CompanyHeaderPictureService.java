package az.project.eracon.service;

import az.project.eracon.entity.CompanyHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.CompanyHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyHeaderPictureService {

    private final CompanyHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(existing -> {
            // Optionally delete the old file from storage if stored physically
            repository.delete(existing);
        });

        CompanyHeaderPictureEntity newPicture = new CompanyHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        CompanyHeaderPictureEntity picture = repository.findById(id)
                .orElseThrow(() -> new CustomException(
                        "Şəkil tapılmadı",
                        "Picture not found",
                        "Not Found",
                        404,
                        null
                ));

        repository.delete(picture);
    }


    public String getPictureUrl() {
        return repository.findTopByOrderByIdAsc()
                .map(CompanyHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}
