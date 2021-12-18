package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "transaction")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @DynamoDBHashKey(attributeName = "transaction_id")
    private String transactionId;

    @DynamoDBAttribute(attributeName = "amount")
    private BigDecimal amount;

    @DynamoDBAttribute(attributeName = "source_account_number")
    private String sourceAccountNumber;

    @DynamoDBAttribute(attributeName = "destination_account_number")
    private String destinationAccountNumber;

    @DynamoDBAttribute(attributeName = "creation_time")
    private String creationTime;

    @DynamoDBAttribute(attributeName = "processing_time")
    private String processingTime;

    @DynamoDBAttribute(attributeName = "created_by")
    private String createdBy;

    @DynamoDBAttribute(attributeName = "processed")
    private boolean processed;
}
