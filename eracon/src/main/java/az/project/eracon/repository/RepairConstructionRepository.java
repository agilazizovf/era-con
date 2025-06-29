package az.project.eracon.repository;

import az.project.eracon.entity.RepairConstructionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepairConstructionRepository extends JpaRepository<RepairConstructionEntity, Long> {
}
