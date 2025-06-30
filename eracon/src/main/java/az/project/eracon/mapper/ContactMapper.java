package az.project.eracon.mapper;

import az.project.eracon.dto.response.ContactResponse;
import az.project.eracon.entity.ContactEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ContactMapper {
    public static ContactResponse convertToDTO(ContactEntity contact) {
        ContactResponse response = new ContactResponse();
        response.setId(contact.getId());
        response.setLocation(contact.getLocation());
        response.setEmail(contact.getEmail());
        response.setMobilePhone(contact.getMobilePhone());
        response.setTelephone(contact.getTelephone());

        return response;
    }

    public static List<ContactResponse> convertToDTOList(List<ContactEntity> contactEntities) {
        return contactEntities.stream()
                .map(ContactMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
