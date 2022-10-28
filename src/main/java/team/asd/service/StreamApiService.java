package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApiService implements IsStreamApiService {

	@Override
	public @NonNull Stream<?> getNonNullStreamItems(Collection<?> collection) {
		if (CollectionUtils.isEmpty(collection)) {
			return Stream.empty();
		}
		return collection.stream()
				.filter(Objects::nonNull);
	}

	@Override
	public @NonNull List<Integer> defineListFromRange(Integer start, Integer end) throws NumberFormatException {
		if (ObjectUtils.anyNull(start, end)) {
			return Collections.emptyList();
		}

		return IntStream.rangeClosed(start < end ? start : end, start > end ? start : end)
				.boxed()
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<Integer> convertStringListToIntegerList(List<String> stringList) {
		if (CollectionUtils.isEmpty(stringList)) {
			return Collections.emptyList();
		}

		return stringList.stream()
				.filter(Objects::nonNull)
				.map(this::toInt)
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}

	private Integer toInt(String str) {
		try {
			return Integer.valueOf(str);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	@Override
	public @NonNull IntStream convertStringToLegalChars(String value) {
		if (StringUtils.isEmpty(value)) {
			return IntStream.empty();
		}

		return value.chars()
				.filter(character -> (character >= 'A' && character <= 'z') || Character.isDigit(character));
	}

	@Override
	public @NonNull BigDecimal sumAllValues(List<BigDecimal> values) {
		return values.stream()
				.filter(Objects::nonNull)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	@Override
	public @NonNull Stream<LocalDate> sortLocalDateList(List<LocalDate> listOfDates) {
		if (CollectionUtils.isEmpty(listOfDates)) {
			return Stream.empty();
		}

		return listOfDates.stream()
				.filter(Objects::nonNull)
				.sorted();
	}

	@Override
	public @NonNull Stream<LocalDate> skipDaysFromSpecifiedDate(List<LocalDate> listOfDates, LocalDate date, Integer daysToSkip) {
		if (CollectionUtils.isEmpty(listOfDates) || ObjectUtils.anyNull(date, daysToSkip) || daysToSkip < 0) {
			return Stream.empty();
		}
		
		return sortLocalDateList(listOfDates).filter(dayIn -> dayIn.isAfter(date) || dayIn.equals(date))
				.skip(daysToSkip);
	}

	@Override
	public @NonNull List<? extends Object> collectLists(List<?>... lists) {
		if (ArrayUtils.isEmpty(lists)) {
			return Collections.emptyList();
		}

		return Arrays.stream(lists)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
	}

	@Override
	public @NonNull List<? extends Object> removeDuplicates(List<?> listWithDuplicates) {
		if (CollectionUtils.isEmpty(listWithDuplicates)) {
			return Collections.emptyList();
		}

		return listWithDuplicates.stream()
				.distinct()
				.collect(Collectors.toList());
	}
}
