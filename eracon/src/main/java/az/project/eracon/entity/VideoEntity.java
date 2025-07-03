package az.project.eracon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "videos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "video_files", joinColumns = @JoinColumn(name = "video_id"))
    @Column(name = "video_url")
    private List<String> mediaUrls;
}
