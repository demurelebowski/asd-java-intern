package team.asd.tutorials.service;

import org.apache.commons.lang3.ObjectUtils;
import team.asd.tutorials.constants.DateElement;
import team.asd.tutorials.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Objects;

public class CalendarService implements IsCalendarService {
	final private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Override
	public String toString(LocalDate date) {
		if (Objects.isNull(date)) {
			return null;
		}

		return date.format(dateFormatter);
	}

	@Override
	public LocalDate toLocalDate(String stringDate) {
		if (Objects.isNull(stringDate)) {
			return null;
		}

		try {
			return LocalDate.parse(stringDate, dateFormatter);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit) {
		if (ObjectUtils.anyNull(unit, toDate, fromDate)) {
			throw new WrongArgumentException("Parameter is wrong");
		}

		try {
			return unit.between(fromDate.atStartOfDay(), toDate.atStartOfDay());
		} catch (Exception e) {
			throw new WrongArgumentException("unit parameter is wrong");
		}
	}

	@Override
	public String getInfo(LocalDate date, DateElement dateElement) {
		if (ObjectUtils.anyNull(dateElement, date)) {
			throw new WrongArgumentException("Parameter is wrong");
		}

		switch (dateElement) {
		case DAY_OF_WEEK:
			return date.getDayOfWeek()
					.toString();
		case WEEK_NUMBER:
			return String.valueOf((int) Math.ceil((date.get(ChronoField.DAY_OF_YEAR) + LocalDate.ofYearDay(date.getYear(), 1)
					.getDayOfWeek()
					.getValue() - 1) / 7.0));
		case MONTH:
			return date.getMonth()
					.toString();
		case IS_LEAP_YEAR:
			return date.isLeapYear() ? "Yes" : "No";
		}
		return null;
	}

	@Override
	public LocalDate reformatToLocalDate(String dateString) throws DateTimeException {
		if (Objects.isNull(dateString)) {
			throw new DateTimeException("Wrong parameter");
		}
		String[] arrDate = dateString.split(" ");

		if (arrDate.length != 3) {
			throw new DateTimeException("Wrong parameter");
		}

		try {
			arrDate[0] = arrDate[0].replaceAll("[^\\d.]", "");
			String stringDate = arrDate[0] + arrDate[1] + arrDate[2];
			int year = Integer.parseInt(arrDate[2]);

			if (year < 1000 || year > 3000) {
				throw new DateTimeException("Wrong parameter");
			}

			return LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dMMMyyyy")
					.withLocale(Locale.ENGLISH));

		} catch (Exception e) {
			throw new DateTimeException("Wrong parameter");
		}
	}
}
