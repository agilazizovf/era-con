package az.project.eracon.dto.request;

import lombok.Data;

@Data
public class AddContactRequest {

    private String location;
    private String email;
    private String mobilePhone;
    private String telephone;
}
