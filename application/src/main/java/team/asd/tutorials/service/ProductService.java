package team.asd.tutorials.service;

import lombok.NonNull;
import org.apache.commons.collections4.CollectionUtils;
import team.asd.tutorials.constants.ProductState;
import team.asd.tutorials.entities.IsProduct;
import team.asd.tutorials.exceptions.WrongProductException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

public class ProductService implements IsProductService {
	@Override
	public @NonNull List<String> defineProductNames(List<IsProduct> productList) throws WrongProductException {

		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}

		if (productList.stream()
				.anyMatch(Objects::isNull)) {
			throw new WrongProductException("Wrong product.");
		}

		return productList.stream()
				.filter(Objects::nonNull)
				.map(IsProduct::getName)
				.collect(Collectors.toList());
	}

	@Override
	public List<IsProduct> defineProductsWithCreatedState(List<IsProduct> productList) {

		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}

		return productList.stream()
				.filter(Objects::nonNull)
				.filter(product -> Objects.nonNull(product.getState()) && product.getState()
						.equals(ProductState.Created))
				.collect(Collectors.toList());

	}

	@Override
	public @NonNull Map<ProductState, Integer> calculateProductCountByState(List<IsProduct> productList) throws WrongProductException {

		Map<ProductState, Integer> mapState = Arrays.stream(ProductState.values())
				.collect(toMap(k -> k, v -> 0));

		if (Objects.isNull(productList)) {
			return mapState;
		}

		if (productList.stream()
				.anyMatch(Objects::isNull)) {
			throw new WrongProductException("Wrong product.");
		}

		mapState.putAll(productList.stream()
				.collect(groupingBy(IsProduct::getState, Collectors.summingInt(e -> 1))));

		return mapState;

	}

	@Override
	public @NonNull List<IsProduct> filterProductsByProvidedObject(List<IsProduct> productList, IsProduct product) throws WrongProductException {

		if (CollectionUtils.isEmpty(productList)) {
			return Collections.emptyList();
		}
		if (Objects.isNull(product)) {
			throw new WrongProductException("Wrong product.");
		}

		return productList.stream()
				.filter(Objects::nonNull)
				.filter(prod -> product.getName() == null || prod.getName()
						.equals(product.getName()))
				.filter(prod -> product.getState() == null || prod.getState()
						.equals(product.getState()))
				.collect(Collectors.toList());

	}

}
