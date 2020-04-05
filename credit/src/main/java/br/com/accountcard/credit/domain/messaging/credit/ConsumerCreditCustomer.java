package br.com.accountcard.credit.domain.messaging.credit;

import br.com.accountcard.credit.domain.dto.CustomerDto;
import br.com.accountcard.credit.domain.service.AnalyseCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(CreditCustomer.class)
public class ConsumerCreditCustomer {

    private final AnalyseCreditService analyseCreditService;

    @StreamListener(CreditCustomer.INPUT)
    public void process(final CustomerDto customerDto) {
        analyseCreditService.process(customerDto);
    }
}
