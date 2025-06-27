package az.project.eracon.repository;

import az.project.eracon.entity.AboutEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AboutRepository extends JpaRepository<AboutEntity, Long> {
}
