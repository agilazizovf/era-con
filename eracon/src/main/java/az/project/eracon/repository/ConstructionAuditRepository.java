package az.project.eracon.repository;

import az.project.eracon.entity.ConstructionAuditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConstructionAuditRepository extends JpaRepository<ConstructionAuditEntity, Long> {
}
