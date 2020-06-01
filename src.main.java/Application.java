

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.chirko.course.entity.UserRepository;


@EnableAutoConfiguration
@ComponentScan({"com.chirko.course.controller","com.chirko.course.entity"})
@Configuration
@EntityScan("com.chirko.course.entity")
@EnableJpaRepositories("com.chirko.course.entity")
@SpringBootApplication
public class Application{


	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


}