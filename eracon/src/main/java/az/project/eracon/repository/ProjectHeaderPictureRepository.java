package az.project.eracon.repository;

import az.project.eracon.entity.ProjectHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectHeaderPictureRepository extends JpaRepository<ProjectHeaderPictureEntity, Long> {
    Optional<ProjectHeaderPictureEntity> findTopByOrderByIdAsc();
}