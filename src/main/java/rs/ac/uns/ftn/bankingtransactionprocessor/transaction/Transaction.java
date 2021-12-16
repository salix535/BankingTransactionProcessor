package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@DynamoDBTable(tableName = "Transaction")
@Builder
@Getter
public class Transaction {

    @DynamoDBHashKey(attributeName = "transaction_id")
    private String transactionId;

    @DynamoDBAttribute(attributeName = "amount")
    private BigDecimal amount;

    @DynamoDBAttribute(attributeName = "source_account_number")
    private String sourceAccountNumber;

    @DynamoDBAttribute(attributeName = "destination_account_number")
    private String destinationAccountNumber;

    @DynamoDBAttribute(attributeName = "time")
    private ZonedDateTime time;
}
