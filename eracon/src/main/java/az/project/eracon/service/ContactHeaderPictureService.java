package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.HeaderPictureResponse;
import az.project.eracon.entity.ContactHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.ContactHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ContactHeaderPictureService {

    private final ContactHeaderPictureRepository repository;
    private final FileService fileService;

    public HeaderPictureResponse uploadPicture(MultipartFile picture) throws IOException {
        if (picture == null || picture.isEmpty()) {
            throw new CustomException("Şəkil boş ola bilməz", "Picture cannot be empty", "Bad Request", 400, null);
        }

        ResponseEntity<FileResponse> uploadResponse = fileService.uploadFile(picture);
        String pictureUrl = uploadResponse.getBody().getUuidName();

        repository.findTopByOrderByIdAsc().ifPresent(repository::delete);

        ContactHeaderPictureEntity newPicture = new ContactHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);

        HeaderPictureResponse response = new HeaderPictureResponse();
        response.setId(newPicture.getId());
        response.setUrl(newPicture.getPictureUrl());

        return response;
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