package in.gagan.roverbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Rover bot application. This application provides the RESTapi calls to update
 * the position of the rover bot and also provide the current position.
 * 
 * @author thindgagan
 *
 */
@SpringBootApplication
public class RoverBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoverBotApplication.class, args);
	}

}
