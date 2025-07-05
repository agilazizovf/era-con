package az.project.eracon.repository;

import az.project.eracon.entity.ProjectPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectPictureRepository extends JpaRepository<ProjectPictureEntity, Long> {
}
