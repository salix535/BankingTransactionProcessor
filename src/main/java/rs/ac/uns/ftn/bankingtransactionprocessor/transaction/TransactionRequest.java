package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TransactionRequest {

    private final UUID id = UUID.randomUUID();

    @NotNull
    @Positive
    private final BigDecimal amount;

    @NotEmpty
    private final String sourceAccountNumber;

    @NotEmpty
    private final String destinationAccountNumber;

    @Setter
    private ZonedDateTime time;
}
