package az.project.eracon.service;

import az.project.eracon.entity.ServiceHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.ServiceHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceHeaderPictureService {

    private final ServiceHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        ServiceHeaderPictureEntity newPicture = new ServiceHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        ServiceHeaderPictureEntity picture = repository.findById(id)
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
                .map(ServiceHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}

