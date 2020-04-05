package br.com.accountcard.fraud.domain.messaging.fraud;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface AnalyzeFraud {

    String INPUT = "analyze-fraud";

    @Input(value = INPUT)
    SubscribableChannel output();
}
