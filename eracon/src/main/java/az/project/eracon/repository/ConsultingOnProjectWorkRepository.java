package az.project.eracon.repository;

import az.project.eracon.entity.ConsultingOnProjectWorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultingOnProjectWorkRepository extends JpaRepository<ConsultingOnProjectWorkEntity, Long> {
}
