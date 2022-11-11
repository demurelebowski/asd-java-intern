package team.asd.controller;

import team.asd.entity.TestMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@RestController
public class TestMessageController {
	@GetMapping("/test/message")
	public TestMessage getTestMessage() {
		return new TestMessage(LocalDate.now(), "Test message from Spring Boot Application!");
	}

	@PostMapping("/test/message")
	public TestMessage postTestMessage(HttpServletRequest request) {
		var content = request.getParameter("content");
		return new TestMessage(content);
	}
}
