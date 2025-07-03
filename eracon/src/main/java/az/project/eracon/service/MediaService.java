package az.project.eracon.service;

import az.project.eracon.dto.request.AddVideoRequest;
import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.DocumentEntity;
import az.project.eracon.entity.PictureEntity;
import az.project.eracon.entity.VideoEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.DocumentMapper;
import az.project.eracon.mapper.PictureMapper;
import az.project.eracon.mapper.VideoMapper;
import az.project.eracon.mapper.VideoResponse;
import az.project.eracon.repository.DocumentRepository;
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
public class MediaService {

    private final PictureRepository pictureRepository;
    private final VideoRepository videoRepository;
    private final DocumentRepository documentRepository;
    private final FileService fileService;

    public MediaResponse uploadPictures(MultipartFile[] files) throws IOException {
        // Optional: delete all previous picture records (if only one allowed per user)
        List<PictureEntity> existing = pictureRepository.findAll();
        for (PictureEntity entity : existing) {
            for (String url : entity.getMediaUrls()) {
                fileService.deleteFile(url); // Implement this in your fileService
            }
            pictureRepository.delete(entity);
        }

        List<String> uploadedUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        PictureEntity mediaEntity = new PictureEntity();
        mediaEntity.setMediaUrls(uploadedUrls);
        pictureRepository.save(mediaEntity);

        return PictureMapper.convertToDTO(mediaEntity);
    }



    public VideoResponse uploadVideos(AddVideoRequest request) {
        VideoEntity video = new VideoEntity();
        video.setVideoUrl(request.getVideoUrl());
        videoRepository.save(video);

        return VideoMapper.convertToDTO(video);
    }

    public MediaResponse uploadDocuments(MultipartFile[] files) throws IOException {
        List<DocumentEntity> existing = documentRepository.findAll();
        for (DocumentEntity entity : existing) {
            for (String url : entity.getMediaUrls()) {
                fileService.deleteFile(url); // Implement delete in fileService
            }
            documentRepository.delete(entity);
        }

        List<String> uploadedUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file);
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        DocumentEntity mediaEntity = new DocumentEntity();
        mediaEntity.setMediaUrls(uploadedUrls);
        documentRepository.save(mediaEntity);

        return DocumentMapper.convertToDTO(mediaEntity);
    }


//    private MediaEntity uploadMedia(MultipartFile[] files) throws IOException {
//        List<String> uploadedUrls = new ArrayList<>();
//
//        for (MultipartFile file : files) {
//            ResponseEntity<FileResponse> response = fileService.uploadFile(file);
//            uploadedUrls.add(response.getBody().getUuidName());
//        }
//
//        MediaEntity mediaEntity = new MediaEntity();
//        mediaEntity.setMediaUrls(uploadedUrls);
//        return mediaRepository.save(mediaEntity);
//    }

    public List<MediaResponse> getAllPictures() {
        return pictureRepository.findAll().stream()
                .map(PictureMapper::convertToDTO)
                .toList();
    }
    public List<VideoResponse> getAllVideos() {
        return videoRepository.findAll().stream()
                .map(VideoMapper::convertToDTO)
                .toList();
    }
    public List<MediaResponse> getAllDocuments() {
        return documentRepository.findAll().stream()
                .map(DocumentMapper::convertToDTO)
                .toList();
    }

    public MediaResponse getPictureById(Long id) {
        PictureEntity picture = pictureRepository.findById(id)
                .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not Found", 404, null));
        return PictureMapper.convertToDTO(picture);
    }
    public VideoResponse getVideoById(Long id) {
        VideoEntity video = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video tapılmadı", "Video not found", "Not Found", 404, null));
        return VideoMapper.convertToDTO(video);
    }
    public MediaResponse getDocumentById(Long id) {
        DocumentEntity document = documentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sənəd tapılmadı", "Document not found", "Not Found", 404, null));
        return DocumentMapper.convertToDTO(document);
    }


//    public MediaResponse updateMedia(Long id, MultipartFile[] files) throws IOException {
//        MediaEntity existing = mediaRepository.findById(id)
//                .orElseThrow(() -> new CustomException("Media tapılmadı", "Media not found", "Not Found", 404, null));
//
//        // Delete old files from their folders — here you must know the folder,
//        // so either store it or assume a default folder, e.g. "media"
//        for (String oldFile : existing.getMediaUrls()) {
//            fileService.deleteFile(oldFile); // or dynamically find folder
//        }
//
//        // Upload new files to the same folder ("media")
//        List<String> newUrls = new ArrayList<>();
//        for (MultipartFile file : files) {
//            ResponseEntity<FileResponse> response = fileService.uploadFile(file); // no type param now
//            newUrls.add(response.getBody().getUuidName());
//        }
//
//        existing.setMediaUrls(newUrls);
//        return MediaMapper.convertToDTO(mediaRepository.save(existing));
//    }
public void deletePicture(Long id) {
    PictureEntity media = pictureRepository.findById(id)
            .orElseThrow(() -> new CustomException("Şəkil tapılmadı", "Picture not found", "Not Found", 404, null));

    media.getMediaUrls().forEach(fileService::deleteFile);
    pictureRepository.delete(media);
}

    public void deleteVideo(Long id) {
        VideoEntity media = videoRepository.findById(id)
                .orElseThrow(() -> new CustomException("Video tapılmadı", "Video not found", "Not Found", 404, null));
        videoRepository.delete(media);
    }

    public void deleteDocument(Long id) {
        DocumentEntity media = documentRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sənəd tapılmadı", "Document not found", "Not Found", 404, null));

        media.getMediaUrls().forEach(fileService::deleteFile);
        documentRepository.delete(media);
    }

}
