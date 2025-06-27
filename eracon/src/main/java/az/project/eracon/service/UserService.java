package az.project.eracon.service;

import az.project.eracon.entity.UserEntity;
import az.project.eracon.exception.CustomException;
import az.project.eracon.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException("İstifadəçi tapılmadı", "User not found", "Not found",
                        404, null));
    }
}
