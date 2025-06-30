package az.project.eracon.controller;

import az.project.eracon.dto.request.AddPartnerRequest;
import az.project.eracon.dto.request.UpdatePartnerRequest;
import az.project.eracon.dto.response.PartnerResponse;
import az.project.eracon.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@MyRestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PartnerResponse> add(@RequestBody AddPartnerRequest request) {
        return ResponseEntity.ok(partnerService.add(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PartnerResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(partnerService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PartnerResponse>> findAll() {
        return ResponseEntity.ok(partnerService.findAll());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<PartnerResponse> update(@RequestBody UpdatePartnerRequest request) {
        return ResponseEntity.ok(partnerService.update(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partnerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{id}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public PartnerResponse uploadPicture(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws IOException {
        return partnerService.uploadPicture(id, file);
    }

    @DeleteMapping("/{id}/delete-picture")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deletePicture(@PathVariable Long id) {
        partnerService.deletePicture(id);
        return ResponseEntity.noContent().build();
    }
}