package az.project.eracon.service;

import az.project.eracon.dto.response.FileResponse;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public ResponseEntity<FileResponse> uploadFile(MultipartFile file) throws IOException {
        Map<?, ?> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());

        String secureUrl = uploadResult.get("secure_url").toString();

        FileResponse response = FileResponse.builder()
                .uuidName(secureUrl)
                .build();

        return ResponseEntity.ok(response);
    }
}
