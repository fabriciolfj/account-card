package br.com.accountcard.email.domain.messaging.notify;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface NotifyMail {

    String INPUT = "notify-stage";

    @Input(value = INPUT)
    SubscribableChannel input();
}
