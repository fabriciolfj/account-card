package br.com.accountcard.email.domain.messaging.notify;

import br.com.accountcard.domain.customer.StatusProposal;
import br.com.accountcard.email.domain.dto.CustomerDTO;
import br.com.accountcard.email.domain.exception.ProposalTypeNotFound;
import br.com.accountcard.email.domain.facade.create.CreateMessageMail;
import br.com.accountcard.email.domain.service.CustomerService;
import br.com.accountcard.email.domain.service.SendEmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import static br.com.accountcard.domain.customer.StatusProposal.ANALYSE_CREDIT;
import static br.com.accountcard.domain.customer.StatusProposal.ANALYSE_DOC;
import static br.com.accountcard.domain.customer.StatusProposal.ANALYSE_FRAUD;
import static br.com.accountcard.domain.customer.StatusProposal.APPROVED;

@EnableBinding(NotifyMail.class)
@Component
@RequiredArgsConstructor
public class ConsumerNotifyMail {

    private final CreateMessageMail createMessageMail;
    private final CustomerService customerService;
    private final SendEmailService mail;
    private final static Logger LOG = LoggerFactory.getLogger(ConsumerNotifyMail.class);

    @StreamListener(value = NotifyMail.INPUT)
    public void process(final CustomerDTO customerDto) {
        var proposal = StatusProposal.fromValue(customerDto.getStatusProposal());

        if (!proposal.isPresent()) {
            throw new ProposalTypeNotFound("Proposal not found. Type :" + customerDto.getStatusProposal());
        }

        switch (proposal.get()) {
            case ANALYSE_DOC:
                LOG.info("Proposal in analyse doc. CPF: " + customerDto.getCpf());
                mail.send(createMessageMail.create(customerDto.getEmail(), "Proposal doc in analyse"));
                customerService.update(customerDto.getCpf(), ANALYSE_DOC);
                break;
            case ANALYSE_FRAUD:
                LOG.info("Proposal in analyse fraud. CPF: " + customerDto.getCpf());
                mail.send(createMessageMail.create(customerDto.getEmail(), "Proposal fraud in analyse"));
                customerService.update(customerDto.getCpf(), ANALYSE_FRAUD);
                break;
            case ANALYSE_CREDIT:
                LOG.info("Proposal in analyse credit. CPF: " + customerDto.getCpf());
                mail.send(createMessageMail.create(customerDto.getEmail(), "Proposal credit in analyse"));
                customerService.update(customerDto.getCpf(), ANALYSE_CREDIT);
                break;
            case APPROVED:
                LOG.info("Proposal approved credit. CPF: " + customerDto.getCpf());
                mail.send(createMessageMail.create(customerDto.getEmail(), "Proposal approved"));
                customerService.update(customerDto.getCpf(), APPROVED);
                break;
        }
    }
}
