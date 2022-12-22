package team.asd.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@ComponentScan({ "team.asd" })
@MapperScan({ "team.asd.mapper" })
@Profile("demo")
public class DataSourceConfigTest {

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.datasource")
	@Profile({ "test" })
	public DataSource dataSource() {
		return DataSourceBuilder.create()
				.driverClassName("com.mysql.cj.jdbc.Driver")
				.build();
	}

}
