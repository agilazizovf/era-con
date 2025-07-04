package az.project.eracon.service;

import az.project.eracon.entity.PartnerHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.PartnerHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerHeaderPictureService {

    private final PartnerHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        PartnerHeaderPictureEntity newPicture = new PartnerHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        PartnerHeaderPictureEntity picture = repository.findById(id)
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
                .map(PartnerHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}