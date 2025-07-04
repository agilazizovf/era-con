package az.project.eracon.repository;

import az.project.eracon.entity.ServiceHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceHeaderPictureRepository extends JpaRepository<ServiceHeaderPictureEntity, Long> {
    Optional<ServiceHeaderPictureEntity> findTopByOrderByIdAsc();
}

