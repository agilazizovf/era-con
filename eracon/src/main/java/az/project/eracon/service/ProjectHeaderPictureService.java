package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.HeaderPictureResponse;
import az.project.eracon.entity.ProjectHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.ProjectHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProjectHeaderPictureService {

    private final ProjectHeaderPictureRepository repository;
    private final FileService fileService;

    public HeaderPictureResponse uploadPicture(MultipartFile picture) throws IOException {
        if (picture == null || picture.isEmpty()) {
            throw new CustomException("Şəkil boş ola bilməz", "Picture cannot be empty", "Bad Request", 400, null);
        }

        // Upload file using your FileService
        ResponseEntity<FileResponse> uploadResponse = fileService.uploadFile(picture);
        String pictureUrl = uploadResponse.getBody().getUuidName();

        // Delete existing picture if any (assuming only one allowed)
        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        // Save new picture entity
        ProjectHeaderPictureEntity newPicture = new ProjectHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);

        repository.save(newPicture);

        HeaderPictureResponse response = new HeaderPictureResponse();
        response.setId(newPicture.getId());
        response.setUrl(newPicture.getPictureUrl());

        return response;
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

    public HeaderPictureResponse getPictureUrl() {
        return repository.findTopByOrderByIdAsc()
                .map(entity -> {
                    HeaderPictureResponse response = new HeaderPictureResponse();
                    response.setId(entity.getId());
                    response.setUrl(entity.getPictureUrl());
                    return response;
                })
                .orElse(null);
    }
}
