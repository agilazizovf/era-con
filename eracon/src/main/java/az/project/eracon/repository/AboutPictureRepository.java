package az.project.eracon.repository;

import az.project.eracon.entity.AboutPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutPictureRepository extends JpaRepository<AboutPictureEntity, Long> {
}