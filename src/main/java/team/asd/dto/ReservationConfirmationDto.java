package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty(notes = "Reservation confirmation ID", example = "1", required = true)
	@JsonProperty("id")
	private Integer id;

	@Min(value = 1, message = "reservation_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Relation to reservation record", example = "1", required = true)
	@JsonProperty("reservation_id")
	private Integer reservationId;

	@Min(value = 1, message = "channel_partner_id {javax.validation.constraints.Min.message}")
	@ApiModelProperty(notes = "Relation to channel_partner record", example = "5", required = true)
	@JsonProperty("channel_partner_id")
	private Integer channelPartnerId;

	@Size(max = 45, message = "confirmation_id is invalid.")
	@ApiModelProperty(notes = "Confirmation number from Channel side. Reservation's alternative ID in Channel Partner 's DB", example = "4567j", required = true)
	@JsonProperty("confirmation_id")
	private String confirmationId;

	@JsonProperty("created_date")
	@ApiModelProperty(notes = "Date and time when record was created", example = "2022-12-03 16:26:31")
	private String createdDate;

	@JsonProperty("version")
	@ApiModelProperty(notes = "Date when record was updated last time", example = "2022-12-03 16:26:39")
	private String version;
}
