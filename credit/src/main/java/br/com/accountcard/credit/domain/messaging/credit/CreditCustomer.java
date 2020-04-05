package br.com.accountcard.credit.domain.messaging.credit;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface CreditCustomer {

    String INPUT = "credit-customer";

    @Input(value = INPUT)
    MessageChannel output();
}
