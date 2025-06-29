package az.project.eracon.controller;

import az.project.eracon.dto.request.AddTitleDescriptionRequest;
import az.project.eracon.dto.request.UpdateTitleDescriptionRequest;
import az.project.eracon.dto.response.TitleDescriptionResponse;
import az.project.eracon.service.PurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@MyRestController
@RequestMapping("/api/purchases")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService service;

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> add(@RequestBody AddTitleDescriptionRequest request) {
        return ResponseEntity.ok(service.add(request));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TitleDescriptionResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<TitleDescriptionResponse> update(@RequestBody UpdateTitleDescriptionRequest request) {
        return ResponseEntity.ok(service.update(request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
