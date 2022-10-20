package team.asd.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.entities.IsPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonService implements IsPersonService {
	@Override
	public @NonNull List<IsPerson> collectPersonsWithNameStartsWith(List<IsPerson> personList, String prefix) {

		if (CollectionUtils.isEmpty(personList)) {
			return new ArrayList<>();
		}
		if (StringUtils.isEmpty(prefix)) {
			return personList;
		}

		return getFilteredPersonStream(personList).filter(person -> StringUtils.startsWithIgnoreCase(person.getName(), prefix))
				.collect(Collectors.toList());

	}

	@Override
	public @NonNull Map<Integer, List<IsPerson>> collectPersonsByAge(List<IsPerson> personList) {

		if (CollectionUtils.isEmpty(personList)) {
			return Collections.emptyMap();
		}

		return getFilteredPersonStream(personList).collect(Collectors.groupingBy(person -> person.getAge()));

	}

	@Override
	public @NonNull Double calculateAverageAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList)) {
			return 0.0;
		}

		return getFilteredPersonStream(personList).collect(Collectors.averagingInt(person -> person.getAge()));
	}

	@Override
	public @NonNull IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList)) {
			return new IntSummaryStatistics();
		}

		long count = getFilteredPersonStream(personList).count();
		long sum = getPersonSummaryStatistics(personList).getSum();
		int min = getPersonSummaryStatistics(personList).getMin();
		int max = getPersonSummaryStatistics(personList).getMax();

		return new IntSummaryStatistics(count, min, max, sum);

	}

	private Stream<IsPerson> getFilteredPersonStream(List<IsPerson> personList) {
		return personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> person.getAge() != null && person.getAge() >= 0);
	}

	private IntSummaryStatistics getPersonSummaryStatistics(List<IsPerson> personList) {
		return getFilteredPersonStream(personList).mapToInt(person -> person.getAge())
				.summaryStatistics();
	}

}
