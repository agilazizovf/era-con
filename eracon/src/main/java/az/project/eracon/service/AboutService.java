package az.project.eracon.service;

import az.project.eracon.dto.request.AboutPictureRequest;
import az.project.eracon.dto.request.AddAboutRequest;
import az.project.eracon.dto.request.UpdateAboutPictureRequest;
import az.project.eracon.dto.request.UpdateAboutRequest;
import az.project.eracon.dto.response.AboutResponse;
import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.entity.AboutEntity;
import az.project.eracon.entity.AboutPictureEntity;
import az.project.eracon.entity.UserEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.AboutMapper;
import az.project.eracon.repository.AboutPictureRepository;
import az.project.eracon.repository.AboutRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AboutService {

    private final AboutRepository aboutRepository;
    private final AboutPictureRepository aboutPictureRepository;
    private final FileService fileService;
    private final ModelMapper mapper;

    public AboutResponse add(AddAboutRequest request) {
        AboutEntity about = new AboutEntity();
        about.setTitle(request.getTitle());
        about.setDescription(request.getDescription());

        List<AboutPictureEntity> pictureEntities = new ArrayList<>();
        if (request.getPictures() != null) {
            for (AboutPictureRequest pictureRequest : request.getPictures()) {
                AboutPictureEntity picture = new AboutPictureEntity();
                picture.setFileName(pictureRequest.getUrl());
                picture.setAbout(about); // set back-reference
                pictureEntities.add(picture);
            }
        }

        about.setPictures(pictureEntities);
        aboutRepository.save(about);

        return AboutMapper.convertToDTO(about);
    }

    public AboutResponse findById(Long id) {
        AboutEntity aboutEntity = aboutRepository.findById(id)
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found",
                        404, null));
        return AboutMapper.convertToDTO(aboutEntity);
    }

    public List<AboutResponse> findAll() {
        List<AboutEntity> aboutEntities = aboutRepository.findAll();
        return AboutMapper.convertToDTOList(aboutEntities);
    }

    public AboutResponse update(UpdateAboutRequest request) {
        AboutEntity aboutEntity = aboutRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found",
                        404, null));

        aboutEntity.setTitle(request.getTitle());
        aboutEntity.setDescription(request.getDescription());

        // Handle pictures
        List<AboutPictureEntity> updatedPictures = new ArrayList<>();
        if (request.getPictures() != null) {
            for (UpdateAboutPictureRequest picReq : request.getPictures()) {
                AboutPictureEntity picture;
                if (picReq.getId() != null) {
                    // Update existing picture
                    picture = aboutPictureRepository.findById(picReq.getId())
                            .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not found", 404, null));
                    picture.setFileName(picReq.getUrl());
                } else {
                    // Add new picture
                    picture = new AboutPictureEntity();
                    picture.setFileName(picReq.getUrl());
                    picture.setAbout(aboutEntity);
                }
                updatedPictures.add(picture);
            }
        }

        // Remove old pictures not in request
        List<Long> incomingIds = request.getPictures() != null ?
                request.getPictures().stream().map(UpdateAboutPictureRequest::getId).filter(id -> id != null).toList() :
                new ArrayList<>();

        List<AboutPictureEntity> picturesToRemove = aboutEntity.getPictures().stream()
                .filter(existing -> existing.getId() != null && !incomingIds.contains(existing.getId()))
                .toList();

        picturesToRemove.forEach(aboutPictureRepository::delete); // optional: delete from DB

        aboutEntity.getPictures().clear();
        aboutEntity.getPictures().addAll(updatedPictures);

        aboutRepository.save(aboutEntity);

        return AboutMapper.convertToDTO(aboutEntity);
    }

    public void delete(Long id) {
        AboutEntity aboutEntity = aboutRepository.findById(id)
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found",
                        404, null));
        aboutRepository.delete(aboutEntity);
    }
    public AboutResponse uploadPictures(Long aboutId, MultipartFile[] files) throws IOException {
        AboutEntity aboutEntity = aboutRepository.findById(aboutId)
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found", 404, null));

        List<AboutPictureEntity> newPictures = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();

            AboutPictureEntity picture = new AboutPictureEntity();
            picture.setFileName(newFileName);
            picture.setAbout(aboutEntity);
            newPictures.add(picture);
        }

        if (aboutEntity.getPictures() == null) {
            aboutEntity.setPictures(new ArrayList<>());
        }
        // Removed deletion of old pictures entirely
        // So no clearing of the existing pictures list

        aboutEntity.getPictures().addAll(newPictures);

        aboutRepository.save(aboutEntity);

        return AboutMapper.convertToDTO(aboutEntity);
    }


    public void deletePicture(Long pictureId) {
        AboutPictureEntity picture = aboutPictureRepository.findById(pictureId)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not found", 404, null));

        try {
            fileService.deleteFile(picture.getFileName()); // delete from disk
        } catch (CustomException e) {
            System.err.println("Failed to delete file: " + picture.getFileName() + " - " + e.getMessage());
        }

        aboutPictureRepository.delete(picture); // delete from DB
    }



}
