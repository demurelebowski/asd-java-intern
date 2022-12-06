package team.asd.tutorials.util;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import team.asd.tutorials.entities.BaseLocation;
import team.asd.tutorials.entities.IsLocation;
import team.asd.tutorials.entities.TestLocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
	void testChangeBaseLocation() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		BaseLocation baseLocation = new BaseLocation();
		String changeTo = "Mars";

		Method changeBaseLocationMethod = MessageBuilder.class.getDeclaredMethod("changeBaseLocation", BaseLocation.class, String.class);
		changeBaseLocationMethod.setAccessible(true);
		changeBaseLocationMethod.invoke(new MessageBuilder(), baseLocation, changeTo);
		assertEquals(changeTo, FieldUtils.readDeclaredField(baseLocation, "baseLocation", true), "Base location should be changed");
	}
}
