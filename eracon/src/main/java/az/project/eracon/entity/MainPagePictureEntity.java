package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "main_page_pictures")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MainPagePictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "media_page_picture_files", joinColumns = @JoinColumn(name = "media_page_picture_id"))
    @Column(name = "media_page_picture_url")
    private List<String> mediaUrls;
}
