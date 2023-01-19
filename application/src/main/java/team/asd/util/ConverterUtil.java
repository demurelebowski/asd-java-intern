package team.asd.util;

import org.apache.commons.lang3.EnumUtils;
import team.asd.constant.ArchivePriceState;
import team.asd.constant.ArchivePriceType;
import team.asd.constant.EntityType;
import team.asd.constant.ReservationState;
import team.asd.dto.ArchivePriceDto;
import team.asd.dto.ReservationConfirmationDto;
import team.asd.dto.ReservationDto;
import team.asd.entity.ArchivePrice;
import team.asd.entity.Reservation;
import team.asd.entity.ReservationConfirmation;
import team.asd.exceptions.ValidationException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

public class ConverterUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	static final private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
				.guests(reservationDto.getGuests())
				.notes(reservationDto.getNotes())
				.version(dateFromString(reservationDto.getVersion()))
				.state(EnumUtils.getEnumIgnoreCase(ReservationState.class, reservationDto.getState()))
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
				.guests(reservation.getGuests())
				.notes(reservation.getNotes())
				.version(getStringFromVersion(reservation.getVersion()))
				.state(getStringFromEnum(reservation.getState()))
				.build();
	}

	public static List<ReservationDto> convertToReservationDtoList(List<Reservation> reservationList) {
		return reservationList.stream()
				.map(ConverterUtil::convertToReservationDto)
				.collect(Collectors.toList());
	}

	public static ArchivePrice convertToArchivePrice(ArchivePriceDto archivePriceDto) {
		if (Objects.isNull(archivePriceDto)) {
			return null;
		}
		return ArchivePrice.builder()
				.id(archivePriceDto.getId())
				.entityType(EnumUtils.getEnumIgnoreCase(EntityType.class, archivePriceDto.getEntityType()))
				.entityId(archivePriceDto.getEntityId())
				.name(archivePriceDto.getName())
				.state(EnumUtils.getEnumIgnoreCase(ArchivePriceState.class, archivePriceDto.getState()))
				.type(EnumUtils.getEnumIgnoreCase(ArchivePriceType.class, archivePriceDto.getType()))
				.value(archivePriceDto.getValue())
				.version(dateFromString(archivePriceDto.getVersion()))
				.build();
	}

	public static ArchivePriceDto convertToArchivePriceDto(ArchivePrice archivePrice) {
		if (Objects.isNull(archivePrice)) {
			return null;
		}
		return ArchivePriceDto.builder()
				.id(archivePrice.getId())
				.entityType(getStringFromEnum(archivePrice.getEntityType()))
				.entityId(archivePrice.getEntityId())
				.name(archivePrice.getName())
				.state(getStringFromEnum(archivePrice.getState()))
				.type(getStringFromEnum(archivePrice.getType()))
				.value(archivePrice.getValue())
				.version(getStringFromVersion(archivePrice.getVersion()))
				.build();
	}

	public static List<ArchivePrice> convertToArchivePriceList(List<ArchivePriceDto> archivePriceDtoList) {
		if (Objects.isNull(archivePriceDtoList)) {
			return null;
		}
		return archivePriceDtoList.stream()
				.map(ConverterUtil::convertToArchivePrice)
				.collect(Collectors.toList());
	}

	public static List<ArchivePriceDto> convertToArchivePriceDtoList(List<ArchivePrice> archivePriceList) {
		if (Objects.isNull(archivePriceList)) {
			return null;
		}
		return archivePriceList.stream()
				.map(ConverterUtil::convertToArchivePriceDto)
				.collect(Collectors.toList());
	}

	public static ReservationConfirmation convertToReservationConfirmation(ReservationConfirmationDto reservationConfirmationDto) {
		if (Objects.isNull(reservationConfirmationDto)) {
			return null;
		}
		return ReservationConfirmation.builder()
				.id(reservationConfirmationDto.getId())
				.confirmationId(reservationConfirmationDto.getConfirmationId())
				.reservationId(reservationConfirmationDto.getReservationId())
				.createdDate(localDateTimeFromString(reservationConfirmationDto.getCreatedDate()))
				.channelPartnerId(reservationConfirmationDto.getChannelPartnerId())
				.version(dateFromString(reservationConfirmationDto.getVersion()))
				.build();
	}

	public static ReservationConfirmationDto convertToReservationConfirmationDto(ReservationConfirmation reservationConfirmation) {
		if (Objects.isNull(reservationConfirmation)) {
			return null;
		}
		return ReservationConfirmationDto.builder()
				.id(reservationConfirmation.getId())
				.confirmationId(reservationConfirmation.getConfirmationId())
				.reservationId(reservationConfirmation.getReservationId())
				.createdDate(stringFromLocalDateTime(reservationConfirmation.getCreatedDate()))
				.channelPartnerId(reservationConfirmation.getChannelPartnerId())
				.version(getStringFromVersion(reservationConfirmation.getVersion()))
				.build();
	}

	public static List<ReservationConfirmationDto> convertToReservationConfirmationDtoList(List<ReservationConfirmation> reservationConfirmationList) {
		return reservationConfirmationList.stream()
				.map(ConverterUtil::convertToReservationConfirmationDto)
				.collect(Collectors.toList());
	}

	public static LocalDate localDateFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return LocalDate.parse(str, dateFormatter);
		} catch (Exception e) {
			throw new ValidationException("Invalid date format.");
		}
	}

	public static LocalDateTime localDateTimeFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return LocalDateTime.parse(str, dateTimeFormatter);
		} catch (Exception e) {
			throw new ValidationException("Invalid datetime format.");
		}
	}

	private static String getStringFromVersion(Date version) {
		if (Objects.isNull(version)) {
			return null;
		}
		return version.toString();
	}

	private static String stringFromLocalDate(LocalDate localDate) {
		if (Objects.isNull(localDate)) {
			return null;
		}
		return localDate.format(dateFormatter);
	}

	private static String stringFromLocalDateTime(LocalDateTime localDateTime) {
		if (Objects.isNull(localDateTime)) {
			return null;
		}
		return localDateTime.format(dateTimeFormatter);
	}

	private static Date dateFromString(String str) {
		if (Objects.isNull(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss", Locale.ENGLISH).parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	private static String getStringFromEnum(Enum obj) {
		return obj == null ? null : obj.name();
	}

}
