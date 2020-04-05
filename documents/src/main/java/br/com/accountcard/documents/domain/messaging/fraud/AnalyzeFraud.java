package br.com.accountcard.documents.domain.messaging.fraud;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface AnalyzeFraud {

    String OUTPUT = "analyze-fraud";

    @Output(value = OUTPUT)
    MessageChannel output();
}
