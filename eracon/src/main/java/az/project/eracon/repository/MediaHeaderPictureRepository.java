package az.project.eracon.repository;

import az.project.eracon.entity.MediaHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaHeaderPictureRepository extends JpaRepository<MediaHeaderPictureEntity, Long> {
    Optional<MediaHeaderPictureEntity> findTopByOrderByIdAsc();
}
