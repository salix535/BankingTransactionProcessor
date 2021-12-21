package rs.ac.uns.ftn.bankingtransactionprocessor;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingTransactionProcessorApplicationTests {

	static {
		System.setProperty("USER_POOL_ID", "pool-id");
		System.setProperty("SQS_URL", "sqs-url");
		System.setProperty("FRONTEND_URL", "frontend-url");
	}

	@Test
	void contextLoads() {
	}

}
