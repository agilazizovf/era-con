package az.project.eracon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class AuthResponse {
    private String username;
    private String token;
    private Set<String> roles;
}
