package team.asd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationConfirmationInfoDto {
    @Size(max = 45, message = "confirmation_id is invalid.")
    @ApiModelProperty(notes = "Confirmation number from Channel side. Reservation's alternative ID in Channel Partner 's DB", example = "4567j")
    @JsonProperty("confirmation_id")
    private String confirmationId;

    @JsonProperty("created_date")
    @ApiModelProperty(notes = "Date and time when record was created", example = "2022-12-03 16:26:31")
    private String createdDate;
}
