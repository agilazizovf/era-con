package az.project.eracon.dto.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class AddProjectRequest {

    @NotBlank(message = "Yer boş ola bilməz") // location
    @Size(min = 2, max = 100, message = "Yer adı 2-100 simvol arasında olmalıdır")
    private String location;

    @NotBlank(message = "İşin adı boş ola bilməz") // title
    @Size(min = 2, max = 100, message = "İşin adı 2-100 simvol arasında olmalıdır")
    private String title;

    @Positive(message = "Sahə 0-dan böyük olmalıdır") // area
    private double area;

    @NotNull(message = "Başlama tarixi boş ola bilməz") // startDate
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;

    // endDate optional, amma əlavə yoxlamalar service və ya controller səviyyəsində edilə bilər
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
}