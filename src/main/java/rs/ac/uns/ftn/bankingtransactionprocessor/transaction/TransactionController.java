package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody final TransactionRequest transactionRequest,
                                                  final Authentication principal) {

        transactionService.processTransactionAndSendToSqs(transactionRequest, principal);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllProcessedTransactions() {
        return ResponseEntity.ok(transactionService.getAllProcessedTransactions());
    }
}
