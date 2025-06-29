package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "construction_audits")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConstructionAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    @CollectionTable(
            name = "construction_audit_descriptions",
            joinColumns = @JoinColumn(name = "construction_audit_id")
    )
    private List<String> descriptions;
}
