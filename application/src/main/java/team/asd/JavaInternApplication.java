package team.asd;

import com.antkorwin.xsync.XSync;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaInternApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaInternApplication.class, args);
	}

	@Bean
	public XSync<Integer> xSync() {
		return new XSync<>();
	}

}
