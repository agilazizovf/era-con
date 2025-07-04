package az.project.eracon.repository;

import az.project.eracon.entity.PartnerHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerHeaderPictureRepository extends JpaRepository<PartnerHeaderPictureEntity, Long> {
    Optional<PartnerHeaderPictureEntity> findTopByOrderByIdAsc();
}