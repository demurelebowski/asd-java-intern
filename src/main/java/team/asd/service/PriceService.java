package team.asd.service;

import lombok.NonNull;
import team.asd.entities.IsPerDayPrice;
import team.asd.entities.IsPrice;
import team.asd.exceptions.WrongPriceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.stream.Collectors.groupingBy;

public class PriceService implements IsPriceService {
	@Override
	public @NonNull BigDecimal defineAverageValueFromPerDayPrice(List<IsPerDayPrice> prices) throws WrongPriceException {
		if (prices == null) {
			return BigDecimal.ZERO;
		}

		if (prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(date -> date.getPrice() == null) || prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(date -> date.getDate() == null)) {
			throw new WrongPriceException("Wrong price item was provided");
		}

		Map<LocalDate, Integer> mapByDate = prices.stream()
				.filter(price -> price.getPrice() != null)
				.collect(groupingBy(IsPerDayPrice::getDate, Collectors.summingInt(e -> 1)));

		if (mapByDate.values()
				.stream()
				.mapToInt(i -> i)
				.anyMatch(i -> i > 1)) {
			throw new WrongPriceException("Date of two prices are equal");
		}

		BigDecimal sum = prices.stream()
				.filter(Objects::nonNull)
				.map(IsPerDayPrice::getPrice)
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		long count = prices.stream()
				.filter(Objects::nonNull)
				.filter(price -> price.getPrice() != null)
				.count();

		if (sum.equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}

		return sum.divide(new BigDecimal(count), 0, RoundingMode.HALF_UP);

	}

	@Override
	public @NonNull BigDecimal defineAverageValueFromPrices(List<IsPrice> prices) throws WrongPriceException {

		if (prices == null) {
			return BigDecimal.ZERO;
		}

		if (prices.stream()
				.anyMatch(Objects::isNull)) {
			throw new WrongPriceException("Wrong prices provided");
		}
		if (prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(date -> date.getPrice() == null) || prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(date -> date.getFromDate() == null) || prices.stream()
				.filter(Objects::nonNull)
				.anyMatch(date -> date.getToDate() == null)) {
			throw new WrongPriceException("Wrong price item was provided");
		}

		if (prices.stream()
				.anyMatch(price -> overlaps(price, prices))) {
			throw new WrongPriceException("Dates collide");
		}

		BigDecimal sum = prices.stream()
				.filter(Objects::nonNull)
				.map(price -> new BigDecimal(DAYS.between(price.getFromDate(), price.getToDate())).multiply(price.getPrice()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal count = prices.stream()
				.filter(Objects::nonNull)
				.map(price -> price.getFromDate()
						.equals(price.getToDate()) ? BigDecimal.ONE : new BigDecimal(DAYS.between(price.getFromDate(), price.getToDate())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		if (sum.equals(BigDecimal.ZERO)) {
			return BigDecimal.ZERO;
		}

		System.out.println(sum);
		System.out.println(count);

		return sum.divide(count, 0, RoundingMode.HALF_UP);

	}

	private boolean overlaps(IsPrice price, List<IsPrice> prices) {

		return prices.stream()
				.filter(prc -> !prc.equals(price))
				.anyMatch(prc -> prc.getFromDate()
						.isBefore(price.getToDate()) && prc.getToDate()
						.isAfter(price.getFromDate()));

	}

}
