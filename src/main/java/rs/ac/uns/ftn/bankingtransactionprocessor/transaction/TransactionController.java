package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<String> createTransaction(@Valid @RequestBody final TransactionRequest transactionRequest) {
        final String result = transactionService.processTransactionAndSendToSqs(transactionRequest);
        return ResponseEntity.ok(result);
    }

}
