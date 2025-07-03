package az.project.eracon.controller;

import az.project.eracon.dto.request.AddSocialMediaRequest;
import az.project.eracon.dto.request.UpdateSocialMediaRequest;
import az.project.eracon.dto.response.SocialMediaResponse;
import az.project.eracon.service.SocialMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MyRestController
@RequestMapping("/api/social-media")
@RequiredArgsConstructor
public class SocialMediaController {

    private final SocialMediaService socialMediaService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SocialMediaResponse> add(@RequestBody AddSocialMediaRequest request) {
        return ResponseEntity.ok(socialMediaService.add(request));
    }

    @GetMapping
    public ResponseEntity<List<SocialMediaResponse>> getAll() {
        return ResponseEntity.ok(socialMediaService.getAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SocialMediaResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(socialMediaService.findById(id));
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<SocialMediaResponse> update(@RequestBody UpdateSocialMediaRequest request) {
        return ResponseEntity.ok(socialMediaService.update(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        socialMediaService.delete(id);
        return ResponseEntity.ok("Sosial şəbəkə linki uğurla silindi.");
    }
}
