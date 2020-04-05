package br.com.accountcard.documents.domain.messaging.decline;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.documents.util.JsonUtil;
import br.com.accountcard.documents.util.MessageBuild;
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
