//package az.project.eracon.controller;
//
//import az.project.eracon.dto.response.FileResponse;
//import az.project.eracon.service.CloudinaryService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//@MyRestController
//@RequestMapping("/api/cloudinary")
//@RequiredArgsConstructor
//public class CloudinaryController {
//
//    private final CloudinaryService cloudinaryService;
//
//    @PostMapping("/upload")
//    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
//    public ResponseEntity<FileResponse> upload(@RequestParam("file") MultipartFile file) {
//        try {
//            ResponseEntity<FileResponse> url = cloudinaryService.uploadFile(file);
//            return url;
//        } catch (Exception e) {
//            FileResponse errorResponse = FileResponse.builder()
//                    .uuidName("Fayl yüklənmədi: " + e.getMessage())
//                    .build();
//            return ResponseEntity.status(500).body(errorResponse);
//        }
//    }
//}
