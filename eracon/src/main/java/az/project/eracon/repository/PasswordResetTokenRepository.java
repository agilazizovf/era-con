package az.project.eracon.repository;

import az.project.eracon.entity.PasswordResetTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetTokenEntity, Long> {
	Optional<PasswordResetTokenEntity> findByEmailAndToken(String email, String token);

	void deleteByUser_Email(String email);

	void deleteByToken(String token);
}