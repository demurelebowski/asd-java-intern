package team.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationReport {
	Reservation reservation;
	ReservationConfirmation reservationConfirmation;
	List<ArchivePrice> archivePriceList;
}
