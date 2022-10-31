package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import team.asd.tutorials.entities.IsPerson;

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

		return getFilteredPersonStream(personList).collect(Collectors.groupingBy(IsPerson::getAge));

	}

	@Override
	public @NonNull Double calculateAverageAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList)) {
			return 0.0;
		}

		return getFilteredPersonStream(personList).collect(Collectors.averagingInt(IsPerson::getAge));
	}

	@Override
	public @NonNull IntSummaryStatistics sumAndCountAge(List<IsPerson> personList) {
		if (CollectionUtils.isEmpty(personList)) {
			return new IntSummaryStatistics();
		}

		return getFilteredPersonStream(personList).mapToInt(IsPerson::getAge)
				.summaryStatistics();

	}

	private Stream<IsPerson> getFilteredPersonStream(List<IsPerson> personList) {
		return personList.stream()
				.filter(Objects::nonNull)
				.filter(person -> person.getAge() != null && person.getAge() >= 0);
	}

}
