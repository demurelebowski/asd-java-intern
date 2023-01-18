package team.asd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationReportDto {
	ReservationConfirmationInfoDto reservationConfirmation;
	List<ArchivePriceInfoDto> archivePrice;
	private Integer id;
	private Integer organizationId;
	private Integer agentId;
	private Integer customerId;
	private Integer productId;
	private String state;
	private String fromDate;
	private String toDate;
	private Double price;
	private Double quote;
	private String currency;
	private Integer guests;
	private String notes;
	private String version;
}
