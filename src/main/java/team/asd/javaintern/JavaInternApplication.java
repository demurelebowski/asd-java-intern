package team.asd.javaintern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.asd.javaintern.controller.TestMessageController;

import java.time.LocalDate;

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
	public TestMessageController test_message() {
		return new TestMessageController(LocalDate.now(), "Test message from Spring Boot Application!");
	}
}
