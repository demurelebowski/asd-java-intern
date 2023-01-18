package team.asd.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import redis.clients.jedis.JedisPooled;

@Configuration
@ComponentScan({ "team.asd" })
@PropertySource("redis.properties")
public class AppConfig {

	private @Value("${redis.host}") String redisHost;
	private @Value("${redis.port}") int redisPort;
	private @Value("${redis.password}") String password;

	@Bean
	public JedisPooled resourcePool() {
		return new JedisPooled(redisHost, redisPort, null, password);
	}
}
