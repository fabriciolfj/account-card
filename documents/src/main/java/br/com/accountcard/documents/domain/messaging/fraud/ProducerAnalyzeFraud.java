package br.com.accountcard.documents.domain.messaging.fraud;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.documents.util.JsonUtil;
import br.com.accountcard.documents.util.MessageBuild;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(AnalyzeFraud.class)
public class ProducerAnalyzeFraud {

    private final AnalyzeFraud analyzeFraud;

    public void process(CustomerDto customerDto) {
        var json = JsonUtil.getJson(customerDto);
        var message = MessageBuild.message(json);
        analyzeFraud.output().send(message);
    }
}
