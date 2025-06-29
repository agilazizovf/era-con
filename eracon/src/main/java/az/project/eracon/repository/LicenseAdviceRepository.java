package az.project.eracon.repository;

import az.project.eracon.entity.LicenseAdviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenseAdviceRepository extends JpaRepository<LicenseAdviceEntity, Long> {
}
