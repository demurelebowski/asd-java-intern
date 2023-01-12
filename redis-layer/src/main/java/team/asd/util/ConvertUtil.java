package team.asd.util;

import team.asd.exceptions.ValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
}
