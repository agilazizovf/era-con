
package az.project.eracon.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class ValidationMessageResponse {
	private String field;
	private String message;
}