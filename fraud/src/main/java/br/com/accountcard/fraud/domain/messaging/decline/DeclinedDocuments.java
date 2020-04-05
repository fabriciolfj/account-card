package br.com.accountcard.fraud.domain.messaging.decline;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface DeclinedDocuments {

    String OUPUT = "decline-customer";

    @Output(value = OUPUT)
    MessageChannel output();
}
