package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.MediaResponse;
import az.project.eracon.entity.MediaEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.MediaMapper;
import az.project.eracon.repository.MediaRepository;
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

    private final MediaRepository mediaRepository;
    private final FileService fileService;

    public MediaResponse uploadPictures(MultipartFile[] files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file, "media/pictures");
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setMediaUrls(uploadedUrls);

        mediaRepository.save(mediaEntity);

        return MediaMapper.convertToDTO(mediaEntity);
    }


    public MediaResponse uploadVideos(MultipartFile[] files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file, "media/videos");
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setMediaUrls(uploadedUrls);

        mediaRepository.save(mediaEntity);

        return MediaMapper.convertToDTO(mediaEntity);
    }


    public MediaResponse uploadDocuments(MultipartFile[] files) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> uploaded = fileService.uploadFile(file, "media/documents");
            String newFileName = uploaded.getBody().getUuidName();
            uploadedUrls.add(newFileName);
        }

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setMediaUrls(uploadedUrls);

        mediaRepository.save(mediaEntity);

        return MediaMapper.convertToDTO(mediaEntity);
    }


    private MediaEntity uploadMedia(MultipartFile[] files, String subFolder) throws IOException {
        List<String> uploadedUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> response = fileService.uploadFile(file, subFolder);
            uploadedUrls.add(response.getBody().getUuidName());
        }

        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.setMediaUrls(uploadedUrls);
        return mediaRepository.save(mediaEntity);
    }

    public List<MediaResponse> getAll() {
        return mediaRepository.findAll().stream()
                .map(MediaMapper::convertToDTO)
                .toList();
    }

    public MediaResponse getById(Long id) {
        MediaEntity media = mediaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Media tapılmadı", "Media not found", "Not Found", 404, null));
        return MediaMapper.convertToDTO(media);
    }

    public MediaResponse updateMedia(Long id, MultipartFile[] files) throws IOException {
        MediaEntity existing = mediaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Media tapılmadı", "Media not found", "Not Found", 404, null));

        // Delete old files from their folders — here you must know the folder,
        // so either store it or assume a default folder, e.g. "media"
        for (String oldFile : existing.getMediaUrls()) {
            fileService.deleteFile(oldFile, "media"); // or dynamically find folder
        }

        // Upload new files to the same folder ("media")
        List<String> newUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            ResponseEntity<FileResponse> response = fileService.uploadFile(file, "media"); // no type param now
            newUrls.add(response.getBody().getUuidName());
        }

        existing.setMediaUrls(newUrls);
        return MediaMapper.convertToDTO(mediaRepository.save(existing));
    }
    public void deleteMedia(Long id) {
        MediaEntity media = mediaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Media tapılmadı", "Media not found", "Not Found", 404, null));

        for (String fileName : media.getMediaUrls()) {
            fileService.deleteFile(fileName, "media");  // again, assume default folder
        }

        mediaRepository.delete(media);
    }
}
