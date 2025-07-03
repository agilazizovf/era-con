package az.project.eracon.repository;

import az.project.eracon.entity.DocumentEntity;
import az.project.eracon.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {
}
