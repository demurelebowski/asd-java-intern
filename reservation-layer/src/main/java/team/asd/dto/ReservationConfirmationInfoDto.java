package team.asd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationConfirmationInfoDto {
	private String confirmationId;

	private String createdDate;
}
