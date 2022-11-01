package team.asd.entities;

public class TestLocation implements IsLocation {
	private final String name;
	private final String city;
	private final String street;
	private final String house;

	public TestLocation(String city, String street, String house) {
		this.city = city;
		this.street = street;
		this.house = house;
		this.name = city + " " + street + ", " + house;
	}

	@Override
	public String toString() {
		return name;
	}
}
