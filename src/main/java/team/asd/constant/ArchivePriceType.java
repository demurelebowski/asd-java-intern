package team.asd.constant;

import java.util.Arrays;

public enum ArchivePriceType {
	Tax("TAX"), Fee("FEE"), Price("PRICE"), Info("INFO"), Commission("COMMISSION");
	private final String value;

	ArchivePriceType(String value) {
		this.value = value;
	}

	public static ArchivePriceType getByString(String str) {
		return Arrays.stream(ArchivePriceType.values())
				.filter(e -> e.getValue()
						.equals(str))
				.findAny()
				.orElse(null);
	}

	public String getValue() {
		return value;
	}
}
