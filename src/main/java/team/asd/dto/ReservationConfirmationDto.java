package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationConfirmationDto {
	@Min(value = 1, message = "id {javax.validation.constraints.Min.message}")
	@JsonProperty("id")
	private Integer id;

	@Min(value = 1, message = "reservation_id {javax.validation.constraints.Min.message}")
	@JsonProperty("reservation_id")
	private Integer reservationId;

	@Min(value = 1, message = "channel_partner_id {javax.validation.constraints.Min.message}")
	@JsonProperty("channel_partner_id")
	private Integer channelPartnerId;

	@Size(max = 45, message = "confirmation_id is invalid.")
	@JsonProperty("confirmation_id")
	private String confirmationId;

	@JsonProperty("created_date")
	private String createdDate;

	@JsonProperty("version")
	private String version;
}
