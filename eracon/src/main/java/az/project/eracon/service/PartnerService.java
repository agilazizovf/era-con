package az.project.eracon.service;

import az.project.eracon.dto.request.AddPartnerRequest;
import az.project.eracon.dto.request.UpdatePartnerRequest;
import az.project.eracon.dto.response.FileResponse;
import az.project.eracon.dto.response.PartnerResponse;
import az.project.eracon.entity.PartnerEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.PartnerMapper;
import az.project.eracon.repository.PartnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final FileService fileService;
    private final CloudinaryService cloudinaryService;

    public PartnerResponse add(AddPartnerRequest request) {
        PartnerEntity partner = new PartnerEntity();
        partner.setWebSiteUrl(request.getWebSiteUrl());
        partnerRepository.save(partner);

        return PartnerMapper.convertToDTO(partner);
    }

    public PartnerResponse findById(Long id) {
        PartnerEntity partner = partnerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tərəfdaş tapılmadı", "Partner not found", "Not found", 404, null));
        return PartnerMapper.convertToDTO(partner);
    }

    public List<PartnerResponse> findAll() {
        List<PartnerEntity> partners = partnerRepository.findAll();
        return PartnerMapper.convertToDTOList(partners);
    }

    public PartnerResponse update(UpdatePartnerRequest request) {
        PartnerEntity partner = partnerRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Tərəfdaş tapılmadı", "Partner not found", "Not found", 404, null));
        partner.setWebSiteUrl(request.getWebSiteUrl());
        partnerRepository.save(partner);
        return PartnerMapper.convertToDTO(partner);
    }

    public void delete(Long id) {
        PartnerEntity partner = partnerRepository.findById(id)
                .orElseThrow(() -> new CustomException("Tərəfdaş tapılmadı", "Partner not found", "Not found", 404, null));
        partnerRepository.delete(partner);
    }

    public PartnerResponse uploadPicture(Long partnerId, MultipartFile file) throws IOException {
        PartnerEntity partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new CustomException("Tərəfdaş tapılmadı", "Partner not found", "Not found", 404, null));

        // Delete old picture if exists
        if (partner.getPictureUrl() != null) {
            try {
                fileService.deleteFile(partner.getPictureUrl());
            } catch (CustomException e) {
                System.err.println("Köhnə fayl silinə bilmədi: " + partner.getPictureUrl() + " - " + e.getMessage());
            }
        }

        // Upload new file
        ResponseEntity<FileResponse> uploaded = cloudinaryService.uploadFile(file);
        String newFileName = uploaded.getBody().getUuidName();

        partner.setPictureUrl(newFileName);
        partnerRepository.save(partner);

        return PartnerMapper.convertToDTO(partner);
    }

    public void deletePicture(Long partnerId) {
        PartnerEntity partner = partnerRepository.findById(partnerId)
                .orElseThrow(() -> new CustomException("Tərəfdaş tapılmadı", "Partner not found", "Not found", 404, null));

        try {
            if (partner.getPictureUrl() != null) {
                fileService.deleteFile(partner.getPictureUrl());
            }
        } catch (CustomException e) {
            System.err.println("Failed to delete file: " + partner.getPictureUrl() + " - " + e.getMessage());
        }

        partner.setPictureUrl(null);
        partnerRepository.save(partner);
    }
}
