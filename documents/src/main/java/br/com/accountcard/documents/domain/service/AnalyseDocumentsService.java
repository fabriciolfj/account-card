package br.com.accountcard.documents.domain.service;

import br.com.accountcard.documents.domain.dto.CustomerDto;
import br.com.accountcard.documents.domain.facade.change.CustomerChange;
import br.com.accountcard.documents.domain.messaging.fraud.ProducerAnalyzeFraud;
import br.com.accountcard.documents.domain.messaging.notify.ProducerNotifyMail;
import br.com.accountcard.domain.customer.StatusProposal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AnalyseDocumentsService {

    private final CustomerChange customerChange;
    private final ProducerNotifyMail producerNotifyMail;
    private final ProducerAnalyzeFraud producerAnalyzeFraud;
    private static final Logger LOG = LoggerFactory.getLogger(AnalyseDocumentsService.class);

    public void process(final CustomerDto customerDto) {
        LOG.info("Starting document analysis process. CPF: " + customerDto.getCpf());
        sendNotify(customerDto);
        evaluateData(customerDto);
    }

    private void sendNotify(CustomerDto customerDto) {
        producerNotifyMail.process(customerDto);
    }

    private void evaluateData(CustomerDto customerDto) {
        var random = new Random().nextInt();
        if(random %2 == 0){
            customerDto.setStatusProposal(StatusProposal.ANALYSE_FRAUD.toString());
            customerChange.updateProposal(customerDto);
            sendProcessFraudAnalyse(customerDto);
            return;
        }

        decline(customerDto);
    }

    private void sendProcessFraudAnalyse(CustomerDto customerDto) {
        LOG.info("Starting fraud proposal. CPF "  + customerDto.getCpf());
        producerAnalyzeFraud.process(customerDto);
    }

    private void decline(final CustomerDto customerDto) {
        LOG.info("Decline proposal. CPF "  + customerDto.getCpf());
        customerDto.setStatusProposal(StatusProposal.DECLINE.toString());
        producerNotifyMail.process(customerDto);
    }

}
