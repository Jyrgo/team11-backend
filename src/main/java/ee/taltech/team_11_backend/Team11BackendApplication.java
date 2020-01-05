package ee.taltech.team_11_backend;

import ee.taltech.team_11_backend.repository.GameRepository;
import ee.taltech.team_11_backend.repository.OwnershipRepository;
import ee.taltech.team_11_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Team11BackendApplication {

	public static void main(String[] args) {
		/* Sander DEV */
		SpringApplication.run(Team11BackendApplication.class, args);
	}
	@Bean
	public CommandLineRunner initStoreObj(GameRepository gameRepository, UserRepository userRepository, OwnershipRepository ownershipRepository) {
		return (args) -> {
		};
	}
}
