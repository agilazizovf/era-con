package az.project.eracon.repository;

import az.project.eracon.entity.CompanyHeaderPictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyHeaderPictureRepository extends JpaRepository<CompanyHeaderPictureEntity, Long> {
    Optional<CompanyHeaderPictureEntity> findTopByOrderByIdAsc();
}
