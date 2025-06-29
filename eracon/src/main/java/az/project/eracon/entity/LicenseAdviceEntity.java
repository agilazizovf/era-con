package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "license_advices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenseAdviceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    @CollectionTable(
            name = "license_advice_descriptions",
            joinColumns = @JoinColumn(name = "license_advice_id")
    )
    private List<String> descriptions;
}
