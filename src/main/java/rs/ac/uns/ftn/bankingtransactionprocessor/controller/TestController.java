package rs.ac.uns.ftn.bankingtransactionprocessor.controller;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final AmazonSQS sqs;

    @Value("${aws.sqs.url}")
    private String queueUrl;

    @GetMapping("/hello")
    public String user(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/send-message")
    public String testSendQueueMessage() {
        final SendMessageRequest sendMessageRequest = new SendMessageRequest(queueUrl, "test");
        sqs.sendMessage(sendMessageRequest);
        return "sent!";
    }
}
