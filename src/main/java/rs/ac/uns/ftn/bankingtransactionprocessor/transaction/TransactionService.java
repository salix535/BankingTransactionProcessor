package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.sqs.AmazonSQS;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {

    private final AmazonSQS sqs;

    private final AmazonDynamoDB amazonDynamoDB;

    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.url}")
    private String queueUrl;

    public String processTransactionAndSendToSqs(final TransactionRequest transactionRequest) {
        transactionRequest.setTime(ZonedDateTime.now());

        final DynamoDB dynamoDB = new DynamoDB(amazonDynamoDB);

        final Table transactionTable = dynamoDB.getTable("Transaction");

        final Item item = transactionTable.getItem("transaction_id", "c9ec7fdc-5e9b-11ec-bf63-0242ac130002");

        try {
            sqs.sendMessage(queueUrl, objectMapper.writeValueAsString(transactionRequest));
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
        return item.toJSON();
    }
}
