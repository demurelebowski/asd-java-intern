package javaintern.entity;

import javaintern.constant.ReservationState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reservation {
	private Integer id;
	private Integer organizationId;
	private Integer agentId;
	private Integer customerId;
	private Integer productId;
	private ReservationState state;
	private LocalDate fromDate;
	private LocalDate toDate;
	private Double price;
	private Double quote;
	private String currency;
	private Integer guests;
	private String notes;
	private Date version;
}
