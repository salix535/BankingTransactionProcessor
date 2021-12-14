package rs.ac.uns.ftn.bankingtransactionprocessor.config;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsConfig {

    @Bean
    public AmazonSQS sqsClient() {
       return AmazonSQSClientBuilder.defaultClient();
    }
}
