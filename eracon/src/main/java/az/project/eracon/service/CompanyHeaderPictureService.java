package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.HeaderPictureResponse;
import az.project.eracon.entity.CompanyHeaderPictureEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.CompanyHeaderPictureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class CompanyHeaderPictureService {

    private final CompanyHeaderPictureRepository repository;
    private final FileService fileService;
    private final CloudinaryService cloudinaryService;

    public HeaderPictureResponse uploadPicture(MultipartFile picture) throws IOException {
        if (picture == null || picture.isEmpty()) {
            throw new CustomException("Şəkil boş ola bilməz", "Picture cannot be empty", "Bad Request", 400, null);
        }

        // Upload file via fileService (assuming it returns ResponseEntity<FileResponse>)
        ResponseEntity<FileResponse> uploaded = cloudinaryService.uploadFile(picture);
        String pictureUrl = uploaded.getBody().getUuidName();

        // Delete previous picture if any
        repository.findTopByOrderByIdAsc().ifPresent(existing -> {
            // Optionally delete file physically if needed here
            repository.delete(existing);
        });

        // Save new picture entity
        CompanyHeaderPictureEntity newPicture = new CompanyHeaderPictureEntity();
        newPicture.setPictureUrl(pictureUrl);
        repository.save(newPicture);

        HeaderPictureResponse response = new HeaderPictureResponse();
        response.setId(newPicture.getId());
        response.setUrl(newPicture.getPictureUrl());

        return response;
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
