package team.asd.util;

import org.junit.jupiter.api.Test;
import team.asd.entities.BaseLocation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MessageBuilderTest {

	@Test
	void add() {
		assertEquals("fieldTest: 123", new MessageBuilder().add(123, "fieldTest")
				.getMessage());
		assertEquals("123", new MessageBuilder().add(123, null)
				.getMessage());
	}

	@Test
	void testChangeBaseLocation() throws IllegalAccessException, InvocationTargetException {
		/*
		Class<MessageBuilder> mbClass = MessageBuilder.class;
		Method changeBaseLocation = mbClass.getDeclaredMethod("changeBaseLocation");
		changeBaseLocation.setAccessible(true);
		*/

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