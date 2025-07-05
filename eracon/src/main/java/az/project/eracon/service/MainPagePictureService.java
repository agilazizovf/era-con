package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.DocumentEntity;
import az.project.eracon.entity.MainPagePictureEntity;
import az.project.eracon.entity.PictureEntity;
import az.project.eracon.entity.VideoEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.DocumentMapper;
import az.project.eracon.mapper.MainPagePictureMapper;
import az.project.eracon.mapper.PictureMapper;
import az.project.eracon.mapper.VideoMapper;
import az.project.eracon.repository.DocumentRepository;
import az.project.eracon.repository.MainPagePictureRepository;
import az.project.eracon.repository.PictureRepository;
import az.project.eracon.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainPagePictureService {

    private final MainPagePictureRepository mainPagePictureRepository;
    private final FileService fileService;

    public MediaResponse uploadPictures(MultipartFile[] files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        MainPagePictureEntity mediaEntity = new MainPagePictureEntity();
        mediaEntity.setMediaUrls(uploadedUrls);
        mainPagePictureRepository.save(mediaEntity);

        return MainPagePictureMapper.convertToDTO(mediaEntity);
    }



    public List<MediaResponse> getAllPictures() {
        return mainPagePictureRepository.findAll().stream()
                .map(MainPagePictureMapper::convertToDTO)
                .toList();
    }

    public MediaResponse getPictureById(Long id) {
        MainPagePictureEntity picture = mainPagePictureRepository.findById(id)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not Found", 404, null));
        return MainPagePictureMapper.convertToDTO(picture);
    }
     public void deletePicture(Long id) {
         MainPagePictureEntity picture = mainPagePictureRepository.findById(id)
                 .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not Found", 404, null));

         picture.getMediaUrls().forEach(fileService::deleteFile);
    mainPagePictureRepository.delete(picture);
}


}
