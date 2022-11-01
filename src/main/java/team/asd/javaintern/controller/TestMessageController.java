package team.asd.javaintern.controller;

import java.time.LocalDate;

public class TestMessageController {
	private final LocalDate date;
	private final String content;

	public TestMessageController(LocalDate date, String content) {
		this.date = date;
		this.content = content;
	}

	public TestMessageController(String content) {
		this.date = LocalDate.now();
		this.content = content;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return "TestMessageController{" + "date=" + date + ", content='" + content + '\'' + '}';
	}
}
