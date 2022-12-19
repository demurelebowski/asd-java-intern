package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationConfirmation {
	private Integer id;
	private Integer reservationId;
	private Integer channelPartnerId;
	private String confirmationId;
	private LocalDateTime createdDate;
	private Date version;
}
