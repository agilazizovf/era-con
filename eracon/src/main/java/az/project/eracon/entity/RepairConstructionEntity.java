package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "repair_constructions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairConstructionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    @CollectionTable(
            name = "repair_construction_descriptions",
            joinColumns = @JoinColumn(name = "repair_construction_id")
    )
    private List<String> descriptions;
}
