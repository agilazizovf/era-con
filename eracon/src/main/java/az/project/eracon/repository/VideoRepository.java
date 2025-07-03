package az.project.eracon.repository;

import az.project.eracon.entity.PictureEntity;
import az.project.eracon.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
}
