package az.project.eracon.service;

import az.project.eracon.dto.request.AddAboutRequest;
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

    private final CloudinaryService cloudinaryService;

    public AboutResponse add(AddAboutRequest request) {
        AboutEntity about = new AboutEntity();
        mapper.map(request, about);
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
        mapper.map(request, aboutEntity);
        aboutRepository.save(aboutEntity);

        return AboutMapper.convertToDTO(aboutEntity);
    }

    public void delete(Long id) {
        AboutEntity aboutEntity = aboutRepository.findById(id)
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found",
                        404, null));
        aboutRepository.delete(aboutEntity);
    }
    public List<AboutResponse> uploadPictures(Long aboutId, List<MultipartFile> files) throws IOException {
        AboutEntity aboutEntity = aboutRepository.findById(aboutId)
                .orElseThrow(() -> new CustomException("Məlumat tapılmadı", "Information not found", "Not found", 404, null));

        List<AboutPictureEntity> newPictures = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = cloudinaryService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();

            AboutPictureEntity picture = new AboutPictureEntity();
            picture.setFileName(newFileName);
            picture.setAbout(aboutEntity);
            newPictures.add(picture);
        }

        aboutEntity.getPictures().addAll(newPictures);
        aboutRepository.save(aboutEntity);

        // bütün about'ları götürüb response şəklinə çevir
        List<AboutEntity> allAbouts = aboutRepository.findAll();
        return AboutMapper.convertToDTOList(allAbouts);
    }

    public AboutResponse updatePicture(Long pictureId, MultipartFile file) throws IOException {
        AboutPictureEntity picture = aboutPictureRepository.findById(pictureId)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not found", 404, null));

        // Köhnə fayl adı
        String oldFileName = picture.getFileName();

        // Yeni faylı upload et
        ResponseEntity<FileResponse> uploaded = cloudinaryService.uploadFile(file);
        String newFileName = uploaded.getBody().getUuidName();

        // Şəkilin fayl adını yenilə
        picture.setFileName(newFileName);

        // Yenilənmiş şəkli yaddaşa ver
        aboutPictureRepository.save(picture);

        // Köhnə faylı sistemdən sil (əgər fileService-də silmək metodu varsa)
        fileService.deleteFile(oldFileName);

        // Şəkilin bağlı olduğu AboutEntity-ni al və onu response-a çevir
        AboutEntity aboutEntity = picture.getAbout();

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
