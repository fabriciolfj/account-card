package br.com.accountcard.fraud.domain.messaging.fraud;

import br.com.accountcard.fraud.domain.dto.CustomerDto;
import br.com.accountcard.fraud.domain.service.AnalyseFraudService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(AnalyzeFraud.class)
public class ConsumerAnalyzeFraud {

    private final AnalyseFraudService analyseFraudService;

    @StreamListener(AnalyzeFraud.INPUT)
    public void process(final CustomerDto customerDto) {
        analyseFraudService.process(customerDto);
    }
}
