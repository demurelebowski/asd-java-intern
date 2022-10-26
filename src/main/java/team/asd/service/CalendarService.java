package team.asd.service;

import team.asd.constants.DateElement;
import team.asd.exceptions.WrongArgumentException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Objects;

public class CalendarService implements IsCalendarService {
	@Override
	public String toString(LocalDate date) {
		if (Objects.isNull(date)) {
			return null;
		}

		return date.format(getFormatter());
	}

	private DateTimeFormatter getFormatter() {
		return DateTimeFormatter.ofPattern("yyyy-MM-dd");
	}

	@Override
	public LocalDate toLocalDate(String stringDate) {
		if (Objects.isNull(stringDate)) {
			return null;
		}

		try {
			return LocalDate.parse(stringDate, getFormatter());
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public long defineCountInRange(LocalDate fromDate, LocalDate toDate, ChronoUnit unit) {
		if (Objects.isNull(unit) || Objects.isNull(toDate) || Objects.isNull(fromDate)) {
			throw new WrongArgumentException("Parameter is wrong");
		}

		if (unit.equals(ChronoUnit.SECONDS)) {
			return ChronoUnit.DAYS.between(fromDate, toDate) * 24 * 60 * 60;
		}

		try {
			return unit.between(fromDate, toDate);
		} catch (Exception e) {
			throw new WrongArgumentException("unit parameter is wrong");
		}
	}

	@Override
	public String getInfo(LocalDate date, DateElement dateElement) {
		if (Objects.isNull(dateElement) || Objects.isNull(date)) {
			throw new WrongArgumentException("Parameter is wrong");
		}

		if (dateElement.equals(DateElement.DAY_OF_WEEK)) {
			return date.getDayOfWeek()
					.toString();
		}
		if (dateElement.equals(DateElement.WEEK_NUMBER)) {
			return String.valueOf(date.get(WeekFields.of(Locale.getDefault())
					.weekOfWeekBasedYear()));
		}
		if (dateElement.equals(DateElement.MONTH)) {
			return date.getMonth()
					.toString();
		}
		if (dateElement.equals(DateElement.IS_LEAP_YEAR)) {
			return date.isLeapYear() ? "Yes" : "No";
		}

		throw new WrongArgumentException("Parameter is wrong");
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
