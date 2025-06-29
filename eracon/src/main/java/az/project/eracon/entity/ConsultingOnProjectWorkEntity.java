package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "consulting_on_project_works")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultingOnProjectWorkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    @ElementCollection
    @CollectionTable(
            name = "consulting_on_project_work_descriptions",
            joinColumns = @JoinColumn(name = "consulting_on_project_work_id")
    )
    private List<String> descriptions;
}
