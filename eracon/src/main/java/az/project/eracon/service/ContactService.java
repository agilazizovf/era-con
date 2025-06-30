package az.project.eracon.service;

import az.project.eracon.dto.request.AddContactRequest;
import az.project.eracon.dto.request.UpdateContactRequest;
import az.project.eracon.dto.response.ContactResponse;
import az.project.eracon.entity.ContactEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.mapper.ContactMapper;
import az.project.eracon.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactResponse add(AddContactRequest request) {
        ContactEntity contact = new ContactEntity();
        contact.setLocation(request.getLocation());
        contact.setEmail(request.getEmail());
        contact.setMobilePhone(request.getMobilePhone());
        contact.setTelephone(request.getTelephone());

        contactRepository.save(contact);
        return ContactMapper.convertToDTO(contact);
    }

    public ContactResponse findById(Long id) {
        ContactEntity contact = contactRepository.findById(id)
                .orElseThrow(() -> new CustomException("Əlaqə tapılmadı", "Contact not found", "Not Found", 404, null));
        return ContactMapper.convertToDTO(contact);
    }

    public List<ContactResponse> findAll() {
        List<ContactEntity> contactEntities = contactRepository.findAll();
        return ContactMapper.convertToDTOList(contactEntities);
    }

    public ContactResponse update(UpdateContactRequest request) {
        ContactEntity contact = contactRepository.findById(request.getId())
                .orElseThrow(() -> new CustomException("Əlaqə tapılmadı", "Contact not found", "Not Found", 404, null));

        contact.setLocation(request.getLocation());
        contact.setEmail(request.getEmail());
        contact.setMobilePhone(request.getMobilePhone());
        contact.setTelephone(request.getTelephone());

        contactRepository.save(contact);
        return ContactMapper.convertToDTO(contact);
    }

    public void delete(Long id) {
        ContactEntity contact = contactRepository.findById(id)
                .orElseThrow(() -> new CustomException("Əlaqə tapılmadı", "Contact not found", "Not Found", 404, null));
        contactRepository.delete(contact);
    }
}
