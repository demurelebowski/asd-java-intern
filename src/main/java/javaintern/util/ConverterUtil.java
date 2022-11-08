package javaintern.util;

import javaintern.constant.ReservationState;
import javaintern.dto.ReservationDto;
import javaintern.entity.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class ConverterUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static Reservation convertToReservation(ReservationDto reservationDto) {
		return Reservation.builder()
				.id(reservationDto.getId())
				.agentId(reservationDto.getAgentId())
				.organizationId(reservationDto.getOrganizationId())
				.customerId(reservationDto.getCustomerId())
				.productId(reservationDto.getProductId())
				.fromDate(localDateFromString(reservationDto.getFromDate()))
				.toDate(localDateFromString(reservationDto.getToDate()))
				.price(reservationDto.getPrice())
				.quote(reservationDto.getQuote())
				.currency(reservationDto.getCurrency())
				.guests(reservationDto.getGuests())
				.notes(reservationDto.getNotes())
				.version(dateFromString(reservationDto.getVersion()))
				.state(reservationStateFromString(reservationDto.getState()))
				.build();
	}

	public static ReservationDto convertToReservationDto(Reservation reservation) {
		return ReservationDto.builder()
				.id(reservation.getId())
				.agentId(reservation.getAgentId())
				.organizationId(reservation.getOrganizationId())
				.customerId(reservation.getCustomerId())
				.productId(reservation.getProductId())
				.fromDate(stringFromLocalDate(reservation.getFromDate()))
				.toDate(stringFromLocalDate(reservation.getToDate()))
				.price(reservation.getPrice())
				.quote(reservation.getQuote())
				.currency(reservation.getCurrency())
				.guests(reservation.getGuests())
				.notes(reservation.getNotes())
				.version(reservation.getVersion()
						.toString())
				.state(reservation.getState()
						.toString())
				.build();
	}

	private static LocalDate localDateFromString(String str) {
		return LocalDate.parse(str, dateFormatter);
	}

	private static String stringFromLocalDate(LocalDate localDate) {
		return localDate.format(dateFormatter);
	}

	private static Date dateFromString(String str) {
		try {
			return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH).parse(str);
		} catch (Exception e) {
			return new Date();
		}
	}

	private static ReservationState reservationStateFromString(String str) {
		try {
			return ReservationState.valueOf(str);
		} catch (Exception e) {
			return ReservationState.Initial;
		}
	}
}
