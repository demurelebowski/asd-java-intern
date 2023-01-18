package team.asd.util;

import team.asd.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class ConvertUtil {
	static final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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

	public static Date convertToDateStart(LocalDate dateToConvert) {
		if (Objects.isNull(dateToConvert)) {
			return null;
		}
		return Date.from(dateToConvert.atStartOfDay()
				.atZone(ZoneId.systemDefault())
				.toInstant());
	}

	public static Date convertToDateEnd(LocalDate dateToConvert) {
		if (Objects.isNull(dateToConvert)) {
			return null;
		}
		LocalDateTime endOfDay = dateToConvert.atTime(LocalTime.MAX);

		return Date.from(endOfDay.atZone(ZoneId.systemDefault())
				.toInstant());
	}
}
