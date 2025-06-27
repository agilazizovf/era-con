package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "about_pictures")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutPictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "about_id")
    private AboutEntity about;
}
