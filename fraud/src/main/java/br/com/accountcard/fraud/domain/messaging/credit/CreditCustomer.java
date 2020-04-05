package br.com.accountcard.fraud.domain.messaging.credit;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CreditCustomer {

    String OUPUT = "credit-customer";

    @Output(value = OUPUT)
    MessageChannel output();
}
