package team.asd.constant;

public enum ArchivePriceType {
	Tax("TAX"), Fee("FEE"), Price("PRICE"), Info("INFO"), Commission("COMMISSION");
	private String value;

	ArchivePriceType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public static ArchivePriceType getByString(String str) {
		for (ArchivePriceType archivePriceType : ArchivePriceType.values()) {
			if (archivePriceType.getValue()
					.equals(str)) {
				return archivePriceType;
			}
		}
		return null;
	}
}
