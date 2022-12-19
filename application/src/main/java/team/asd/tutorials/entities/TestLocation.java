package team.asd.tutorials.entities;

public class TestLocation implements IsLocation {
	private final String city;
	private final String street;
	private final String house;

	public TestLocation(String city, String street, String house) {
		this.city = city;
		this.street = street;
		this.house = house;
	}

	@Override
	public String toString() {
		return city + " " + street + ", " + house;
	}
}
