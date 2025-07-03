package az.project.eracon.service;

import az.project.eracon.dto.request.AddSocialMediaRequest;
import az.project.eracon.dto.request.UpdateSocialMediaRequest;
import az.project.eracon.dto.response.SocialMediaResponse;
import az.project.eracon.entity.SocialMediaEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.SocialMediaMapper;
import az.project.eracon.repository.SocialMediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;

    public SocialMediaResponse add(AddSocialMediaRequest request) {
        SocialMediaEntity socialMedia = new SocialMediaEntity();
        socialMedia.setUrl(request.getUrl());
        socialMediaRepository.save(socialMedia);

        return SocialMediaMapper.convertToDTO(socialMedia);
    }

    public List<SocialMediaResponse> getAll() {
        List<SocialMediaEntity> list = socialMediaRepository.findAll();
        return SocialMediaMapper.convertToDTOList(list);
    }

    public SocialMediaResponse findById(Long id) {
        SocialMediaEntity socialMedia = socialMediaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sosial şəbəkə linki tapılmadı", "Social media link not found",
                        "Not found", 404, null));
        return SocialMediaMapper.convertToDTO(socialMedia);
    }

    public SocialMediaResponse update(UpdateSocialMediaRequest request) {
        SocialMediaEntity socialMedia = socialMediaRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Sosial şəbəkə linki tapılmadı", "Social media link not found",
                        "Not found", 404, null));
        socialMedia.setUrl(request.getUrl());
        socialMediaRepository.save(socialMedia);

        return SocialMediaMapper.convertToDTO(socialMedia);
    }

    public void delete(Long id) {
        SocialMediaEntity socialMedia = socialMediaRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sosial şəbəkə linki tapılmadı", "Social media link not found",
                        "Not found", 404, null));
        socialMediaRepository.delete(socialMedia);
    }
}
