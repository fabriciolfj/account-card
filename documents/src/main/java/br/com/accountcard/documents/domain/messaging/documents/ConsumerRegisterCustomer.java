package br.com.accountcard.documents.domain.messaging.documents;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.documents.domain.service.AnalyseDocumentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@EnableBinding(RegisterCustomer.class)
@RequiredArgsConstructor
@Component
public class ConsumerRegisterCustomer {

    private final AnalyseDocumentsService analyseDocumentsService;

    @StreamListener(RegisterCustomer.INPUT)
    public void process(final CustomerDto customer) {
        analyseDocumentsService.process(customer);
    }
}
