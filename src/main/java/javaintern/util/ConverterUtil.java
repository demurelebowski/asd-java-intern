package javaintern.util;

import javaintern.dto.ReservationDto;
import javaintern.entity.Reservation;

public class ConverterUtil {
	public static Reservation convertToReservation(ReservationDto reservationDto) {
		return new Reservation(reservationDto);
	}

	public static ReservationDto convertToReservationDto(Reservation reservation) {
		return new ReservationDto(reservation);
	}
}
