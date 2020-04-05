package br.com.accountcard.documents.domain.messaging.documents;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface RegisterCustomer {

    String INPUT = "register.customer";

    @Input(value = INPUT)
    SubscribableChannel input();
}
