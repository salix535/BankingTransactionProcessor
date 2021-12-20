package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AmazonSQS sqs;

    private final AmazonDynamoDB amazonDynamoDB;

    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.url}")
    private String queueUrl;

    public void processTransactionAndSendToSqs(final TransactionRequest transactionRequest, final String userName) {
        transactionRequest.setCreationTime(ZonedDateTime.now());
        transactionRequest.setCreatedBy(userName);
        try {
            sqs.sendMessage(queueUrl, objectMapper.writeValueAsString(transactionRequest));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }

    public List<Transaction> getAllProcessedTransactions() {
        final DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        final PaginatedScanList<Transaction> transactions = dynamoDBMapper.scan(Transaction.class, new DynamoDBScanExpression());

        return transactions.stream().toList();
    }
}
