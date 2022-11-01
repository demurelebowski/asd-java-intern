package team.asd.javaintern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.javaintern.controller.TestMessageController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
@RestController
public class JavaInternApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaInternApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/test/message")
	public TestMessageController testMessage() {
		return new TestMessageController("Test message from Spring Boot Application!");
	}

	@PostMapping(path = "/test/message")
	public ResponseEntity postTestMessageController(HttpServletRequest request) {
		var content = request.getParameter("content");
		try {
			LocalDate date = java.time.LocalDate.parse(request.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			TestMessageController testMessageController = new TestMessageController(date, content);
			return ResponseEntity.status(200)
					.body(testMessageController.toString());
		} catch (Exception e) {
			return ResponseEntity.status(400)
					.body("Check date(pattern: yyyy-MM-dd)");
		}
	}

	@PutMapping(path = "/test/message")
	public TestMessageController putTestMessageController(HttpServletRequest request) {
		var content = request.getParameter("content");
		return new TestMessageController(content);
	}

	@DeleteMapping(path = "/test/message")
	public ResponseEntity deleteTestMessageController(HttpServletRequest request) {
		return ResponseEntity.ok()
				.build();
	}

}
