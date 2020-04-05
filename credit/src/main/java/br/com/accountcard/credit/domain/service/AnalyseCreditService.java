package br.com.accountcard.credit.domain.service;

import br.com.accountcard.credit.domain.dto.CustomerDto;
import br.com.accountcard.credit.domain.messaging.notify.ProducerNotifyMail;
import br.com.accountcard.domain.customer.StatusProposal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnalyseCreditService {

    private final ProducerNotifyMail producerNotifyMail;
    private static final Logger LOG = LoggerFactory.getLogger(AnalyseCreditService.class);

    public void process(final CustomerDto customerDto) {
        LOG.info("Starting credit analysis process");
        sendNotify(customerDto);
        evaluateData(customerDto);
    }

    private void evaluateData(final CustomerDto customerDto) {
        var random = new Random().nextInt();
        if(random %2 == 0){
            LOG.info("Approved proposal. CPF "  + customerDto.getCpf());
            customerDto.setStatusProposal(StatusProposal.APPROVED.toString());
            sendNotify(customerDto);
            return;
        }

        decline(customerDto);
    }

    private void sendNotify(final CustomerDto customerDto) {
        producerNotifyMail.process(customerDto);
    }

    private void decline(final CustomerDto customerDto) {
        LOG.info("Decline proposal. CPF "  + customerDto.getCpf());
        customerDto.setStatusProposal(StatusProposal.DECLINE.toString());
        producerNotifyMail.process(customerDto);
    }

}
