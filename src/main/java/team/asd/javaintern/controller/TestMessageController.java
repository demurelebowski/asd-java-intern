package team.asd.javaintern.controller;

import java.time.LocalDate;

public class TestMessageController {
	private final LocalDate date;
	private final String content;

	public TestMessageController(LocalDate date, String content) {
		this.date = date;
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
}
