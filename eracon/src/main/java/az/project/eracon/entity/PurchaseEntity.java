package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "purchases")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    @CollectionTable(
            name = "purchase_descriptions",
            joinColumns = @JoinColumn(name = "purchase_id")
    )
    private List<String> descriptions;
}
