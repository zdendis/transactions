package cz.zdenekvlk.transactions;

import cz.zdenekvlk.transactions.service.SolutionInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class TransactionsApplication implements CommandLineRunner {
	private final SolutionInterface solution;

	public TransactionsApplication(SolutionInterface solution) {
		this.solution = solution;
	}

	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Arrays.stream(args).forEach( file -> {
			log.info("File name: " + file);
			log.info(System.lineSeparator() + solution.solution(file));
		});
	}
}
