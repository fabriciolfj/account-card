package br.com.accountcard.authorizer.domain.messages;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface RegisterCustomer {

    String OUTPUT = "register.customer";

    @Output(value = OUTPUT)
    MessageChannel output();
}
