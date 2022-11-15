package team.asd.util;

import team.asd.constant.ReservationState;
import team.asd.dto.ReservationDto;
import team.asd.entity.Reservation;
import team.asd.exceptions.ValidationException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class ConverterUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public static Reservation convertToReservation(ReservationDto reservationDto) {
		if (Objects.isNull(reservationDto)) {
			return null;
		}
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
				.guests(getGuests(reservationDto.getGuests()))
				.notes(reservationDto.getNotes())
				.version(dateFromString(reservationDto.getVersion()))
				.state(reservationStateFromString(reservationDto.getState()))
				.build();
	}

	public static ReservationDto convertToReservationDto(Reservation reservation) {
		if (Objects.isNull(reservation)) {
			return null;
		}
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
				.guests(getGuests(reservation.getGuests()))
				.notes(reservation.getNotes())
				.version(getVersion(reservation.getVersion()))
				.state(stringFromReservationState(reservation.getState()))
				.build();
	}

	private static LocalDate localDateFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return LocalDate.parse(str, dateFormatter);
		} catch (Exception e) {
			throw new ValidationException("Invalid date format.");
		}
	}

	private static String getVersion(Date version) {
		if (Objects.isNull(version)) {
			return null;
		}
		return version.toString();
	}

	private static Integer getGuests(Integer guest) {
		if (Objects.isNull(guest)) {
			return null;
		}
		return guest;
	}

	private static String stringFromLocalDate(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		return localDate.format(dateFormatter);
	}

	private static Date dateFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH).parse(str);
		} catch (Exception e) {
			return new Date();
		}
	}

	private static ReservationState reservationStateFromString(String str) {
		try {
			return Arrays.stream(ReservationState.values())
					.filter(e -> e.name()
							.equalsIgnoreCase(str))
					.findAny()
					.orElse(ReservationState.Initial);
		} catch (Exception e) {
			return ReservationState.Initial;
		}
	}

	private static String stringFromReservationState(ReservationState reservationState) {
		if (Objects.isNull(reservationState)) {
			return null;
		}
		return reservationState.name();
	}
}
