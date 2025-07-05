package az.project.eracon.entity;

import az.project.eracon.entity.ProjectEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project_pictures")
@Getter
@Setter
public class ProjectPictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Fayl adı (UUID ilə adlandırılmış fayl)
    @Column(name = "file_name", nullable = false)
    private String fileName;

    // Project ilə əlaqə Many-to-One
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private ProjectEntity project;
}
