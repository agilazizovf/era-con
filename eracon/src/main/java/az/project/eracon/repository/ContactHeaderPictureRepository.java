package az.project.eracon.repository;

import az.project.eracon.entity.ContactHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactHeaderPictureRepository extends JpaRepository<ContactHeaderPictureEntity, Long> {
    Optional<ContactHeaderPictureEntity> findTopByOrderByIdAsc();
}