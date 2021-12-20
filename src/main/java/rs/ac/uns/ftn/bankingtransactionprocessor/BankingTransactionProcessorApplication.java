package rs.ac.uns.ftn.bankingtransactionprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankingTransactionProcessorApplication {

	static {
		System.setProperty("USER_POOL_ID", "eu-central-1_mycagJ5N3");
		System.setProperty("SQS_URL", "sqs-url");
	}

	public static void main(String[] args) {
		SpringApplication.run(BankingTransactionProcessorApplication.class, args);
	}
}
