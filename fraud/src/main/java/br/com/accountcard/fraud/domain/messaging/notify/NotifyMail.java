package br.com.accountcard.fraud.domain.messaging.notify;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface NotifyMail {

    String OUTPUT = "notify-stage";

    @Output(value = OUTPUT)
    MessageChannel output();
}
