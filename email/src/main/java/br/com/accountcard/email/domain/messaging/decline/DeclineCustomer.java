package br.com.accountcard.email.domain.messaging.decline;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface DeclineCustomer {

    String INPUT = "decline-customer";

    @Input(value = INPUT)
    SubscribableChannel input();
}
