package rs.ac.uns.ftn.bankingtransactionprocessor.transaction;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody final TransactionRequest transactionRequest,
                                                  final JwtAuthenticationToken authentication) {
        final String userName = (String) authentication.getTokenAttributes().get("username");
        transactionService.processTransactionAndSendToSqs(transactionRequest, userName);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllProcessedTransactions() {
        return ResponseEntity.ok(transactionService.getAllProcessedTransactions());
    }
}
