package fils.licenta.ioana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class IoanaApplication {

	public static void main(String[] args) {
		SpringApplication.run(IoanaApplication.class, args);
	}
}
