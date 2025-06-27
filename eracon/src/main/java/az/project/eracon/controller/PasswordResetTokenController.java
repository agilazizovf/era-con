package az.project.eracon.controller;

import az.project.eracon.dto.request.PasswordResetRequest;
import az.project.eracon.dto.response.PasswordResetResponse;
import az.project.eracon.dto.request.ResetRequest;
import az.project.eracon.dto.response.ResetResponse;
import az.project.eracon.service.PasswordResetTokenService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@MyRestController
@RequestMapping("/api/passwords")
@RequiredArgsConstructor
public class PasswordResetTokenController {
	private final PasswordResetTokenService passwordResetTokenService;

	@PostMapping("/reset-request")
	public ResponseEntity<ResetResponse> resetRequest(@RequestBody ResetRequest request) throws MessagingException {
		passwordResetTokenService.initiatePasswordReset(request.getEmail());
		ResetResponse response = new ResetResponse();
		response.setMessage("Password reset link has been sent to your email.");
		return ResponseEntity.ok(response);
	}

	@PostMapping("/reset")
	public PasswordResetResponse resetPassword(@RequestBody PasswordResetRequest request) {
		PasswordResetResponse response = passwordResetTokenService.resetPassword(request.getEmail(), request.getToken(),
				request.getNewPassword());
		return response;
	}

}
