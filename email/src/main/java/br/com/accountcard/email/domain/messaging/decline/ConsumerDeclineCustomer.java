package br.com.accountcard.email.domain.messaging.decline;

import br.com.accountcard.email.domain.dto.CustomerDTO;
import br.com.accountcard.email.domain.facade.change.CustomerChange;
import br.com.accountcard.email.domain.facade.create.CreateMessageMail;
import br.com.accountcard.email.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableBinding(DeclineCustomer.class)
public class ConsumerDeclineCustomer {

    private final SendEmailService envioEmailService;
    private final CreateMessageMail createMessageMail;
    private final CustomerChange customerChange;
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerDeclineCustomer.class);

    @StreamListener(DeclineCustomer.INPUT)
    public void process(final CustomerDTO customerDto) {
        LOG.info("Proposal decline. CPF: " + customerDto.getCpf());
        envioEmailService.send(createMessageMail.create(customerDto.getEmail(), "His proposal was rejected"));
        customerChange.processDecline(customerDto);
    }
}
