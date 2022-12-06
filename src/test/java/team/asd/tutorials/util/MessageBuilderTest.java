package team.asd.tutorials.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.asd.tutorials.entities.BaseLocation;
import team.asd.tutorials.entities.IsLocation;
import team.asd.tutorials.entities.TestLocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageBuilderTest {

	@Test
	void add() {
		Assertions.assertEquals("fieldTest: 123", new MessageBuilder().add(123, "fieldTest")
				.getMessage());
		assertEquals("123", new MessageBuilder().add(123, null)
				.getMessage());
	}

	@Test
	void testAddWithNull() {
		MessageBuilder messageBuilder = new MessageBuilder().add(null, null);
		assertNotNull(messageBuilder.getMessage(), "Should not be null");
	}

	@Test
	void testAddLocation() {
		IsLocation location = new TestLocation("Tschernikow", "Main", "12v");
		String locationText = "Tschernikow Main, 12v";
		MessageBuilder message = new MessageBuilder().add(location);
		assertEquals(locationText, message.getMessage());
	}

	@Test
	void testChangeBaseLocation() throws IllegalAccessException, InvocationTargetException {
		BaseLocation baseLocation = new BaseLocation();
		MessageBuilder messageBuilder = new MessageBuilder();
		String changeTo = "Mars";

		Method method = Arrays.stream(MessageBuilder.class.getDeclaredMethods())
				.filter(met -> met.getName()
						.equals("changeBaseLocation"))
				.findFirst()
				.orElseThrow(NoSuchElementException::new);

		method.setAccessible(true);
		method.invoke(messageBuilder, baseLocation, changeTo);

		assertEquals(changeTo, baseLocation.getBaseLocation());
	}
}
