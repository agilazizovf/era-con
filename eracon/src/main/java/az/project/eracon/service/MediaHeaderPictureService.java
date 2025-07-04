package az.project.eracon.service;

import az.project.eracon.entity.MediaHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.MediaHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MediaHeaderPictureService {

    private final MediaHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        MediaHeaderPictureEntity newPicture = new MediaHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        MediaHeaderPictureEntity picture = repository.findById(id)
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
                .map(MediaHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}