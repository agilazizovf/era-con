package az.project.eracon.controller;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.service.LicenseAdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MyRestController
@RequestMapping("/api/license-advices")
@RequiredArgsConstructor
public class LicenseAdviceController {

    private final LicenseAdviceService licenseAdviceService;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> add(@RequestBody AddTitleDescriptionRequest request) {
        TitleDescriptionResponse response = licenseAdviceService.add(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> findById(@PathVariable Long id) {
        TitleDescriptionResponse response = licenseAdviceService.findById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<TitleDescriptionResponse>> findAll() {
        List<TitleDescriptionResponse> list = licenseAdviceService.findAll();
        return ResponseEntity.ok(list);
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> update(@RequestBody UpdateTitleDescriptionRequest request) {
        TitleDescriptionResponse response = licenseAdviceService.update(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        licenseAdviceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
