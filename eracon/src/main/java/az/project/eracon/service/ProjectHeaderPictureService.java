package az.project.eracon.service;

import az.project.eracon.entity.ProjectHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.ProjectHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectHeaderPictureService {

    private final ProjectHeaderPictureRepository repository;

    public void uploadPicture(String pictureUrl) {
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        ProjectHeaderPictureEntity newPicture = new ProjectHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);
    }

    public void deletePicture(Long id) {
        ProjectHeaderPictureEntity picture = repository.findById(id)
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
                .map(ProjectHeaderPictureEntity::getPictureUrl)
                .orElse(null);
    }
}
