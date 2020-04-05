package br.com.accountcard.fraud.domain.messaging.decline;

import br.com.accountcard.domain.util.JsonUtil;
import br.com.accountcard.domain.util.MessageBuild;
import br.com.accountcard.fraud.domain.dto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(DeclinedDocuments.class)
public class ProducerDeclineDocuments {

    private final DeclinedDocuments declinedDocuments;

    public void process(final CustomerDto customerDto) {
        var json = JsonUtil.getJson(customerDto);
        var message = MessageBuild.message(json);
        declinedDocuments.output().send(message);
    }
}
