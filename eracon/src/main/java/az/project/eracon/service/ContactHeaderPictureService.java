package az.project.eracon.service;

import az.project.eracon.entity.ContactHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.ContactHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactHeaderPictureService {

    private final ContactHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        ContactHeaderPictureEntity newPicture = new ContactHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        ContactHeaderPictureEntity picture = repository.findById(id)
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
                .map(ContactHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}